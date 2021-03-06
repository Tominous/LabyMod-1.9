package org.h2.upgrade;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.UUID;
import org.h2.engine.ConnectionInfo;
import org.h2.jdbc.JdbcConnection;
import org.h2.message.DbException;
import org.h2.store.fs.FileUtils;
import org.h2.util.StringUtils;
import org.h2.util.Utils;

public class DbUpgrade
{
  private static final boolean UPGRADE_CLASSES_PRESENT = Utils.isClassPresent("org.h2.upgrade.v1_1.Driver");
  private static boolean scriptInTempDir;
  private static boolean deleteOldDb;
  
  public static Connection connectOrUpgrade(String url, Properties info)
    throws SQLException
  {
    if (!UPGRADE_CLASSES_PRESENT) {
      return null;
    }
    Properties i2 = new Properties();
    i2.putAll(info);
    
    Object o = info.get("password");
    if ((o instanceof char[])) {
      i2.put("password", StringUtils.cloneCharArray((char[])o));
    }
    info = i2;
    ConnectionInfo ci = new ConnectionInfo(url, info);
    if ((ci.isRemote()) || (!ci.isPersistent())) {
      return null;
    }
    String name = ci.getName();
    if (FileUtils.exists(name + ".h2.db")) {
      return null;
    }
    if (!FileUtils.exists(name + ".data.db")) {
      return null;
    }
    if (ci.removeProperty("NO_UPGRADE", false)) {
      return connectWithOldVersion(url, info);
    }
    synchronized (DbUpgrade.class)
    {
      upgrade(ci, info);
      return null;
    }
  }
  
  public static void setScriptInTempDir(boolean scriptInTempDir)
  {
    scriptInTempDir = scriptInTempDir;
  }
  
  public static void setDeleteOldDb(boolean deleteOldDb)
  {
    deleteOldDb = deleteOldDb;
  }
  
  private static Connection connectWithOldVersion(String url, Properties info)
    throws SQLException
  {
    url = "jdbc:h2v1_1:" + url.substring("jdbc:h2:".length()) + ";IGNORE_UNKNOWN_SETTINGS=TRUE";
    
    return DriverManager.getConnection(url, info);
  }
  
  private static void upgrade(ConnectionInfo ci, Properties info)
    throws SQLException
  {
    String name = ci.getName();
    String data = name + ".data.db";
    String index = name + ".index.db";
    String lobs = name + ".lobs.db";
    String backupData = data + ".backup";
    String backupIndex = index + ".backup";
    String backupLobs = lobs + ".backup";
    String script = null;
    try
    {
      if (scriptInTempDir)
      {
        new File(Utils.getProperty("java.io.tmpdir", ".")).mkdirs();
        script = File.createTempFile("h2dbmigration", "backup.sql").getAbsolutePath();
      }
      else
      {
        script = name + ".script.sql";
      }
      String oldUrl = "jdbc:h2v1_1:" + name + ";UNDO_LOG=0;LOG=0;LOCK_MODE=0";
      
      String cipher = ci.getProperty("CIPHER", null);
      if (cipher != null) {
        oldUrl = oldUrl + ";CIPHER=" + cipher;
      }
      Connection conn = DriverManager.getConnection(oldUrl, info);
      Statement stat = conn.createStatement();
      String uuid = UUID.randomUUID().toString();
      if (cipher != null) {
        stat.execute("script to '" + script + "' cipher aes password '" + uuid + "' --hide--");
      } else {
        stat.execute("script to '" + script + "'");
      }
      conn.close();
      FileUtils.move(data, backupData);
      FileUtils.move(index, backupIndex);
      if (FileUtils.exists(lobs)) {
        FileUtils.move(lobs, backupLobs);
      }
      ci.removeProperty("IFEXISTS", false);
      conn = new JdbcConnection(ci, true);
      stat = conn.createStatement();
      if (cipher != null) {
        stat.execute("runscript from '" + script + "' cipher aes password '" + uuid + "' --hide--");
      } else {
        stat.execute("runscript from '" + script + "'");
      }
      stat.execute("analyze");
      stat.execute("shutdown compact");
      stat.close();
      conn.close();
      if (deleteOldDb)
      {
        FileUtils.delete(backupData);
        FileUtils.delete(backupIndex);
        FileUtils.deleteRecursive(backupLobs, false);
      }
    }
    catch (Exception e)
    {
      if (FileUtils.exists(backupData)) {
        FileUtils.move(backupData, data);
      }
      if (FileUtils.exists(backupIndex)) {
        FileUtils.move(backupIndex, index);
      }
      if (FileUtils.exists(backupLobs)) {
        FileUtils.move(backupLobs, lobs);
      }
      FileUtils.delete(name + ".h2.db");
      throw DbException.toSQLException(e);
    }
    finally
    {
      if (script != null) {
        FileUtils.delete(script);
      }
    }
  }
}
