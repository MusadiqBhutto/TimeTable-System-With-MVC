package com.mbhutto;

import com.mbhutto.view.Home;

import java.sql.SQLException;

public class Main {

    public static void main (String args[])
    {
        try
        {
            Home h = new Home();
        }
        catch (SQLException throwable)
        {
            throwable.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
