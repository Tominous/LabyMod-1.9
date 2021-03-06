package org.h2.api;

import java.sql.Connection;
import java.sql.SQLException;

public abstract interface AggregateFunction
{
  public abstract void init(Connection paramConnection)
    throws SQLException;
  
  public abstract int getType(int[] paramArrayOfInt)
    throws SQLException;
  
  public abstract void add(Object paramObject)
    throws SQLException;
  
  public abstract Object getResult()
    throws SQLException;
}
