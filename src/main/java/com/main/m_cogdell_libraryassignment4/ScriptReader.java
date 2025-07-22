package com.main.m_cogdell_libraryassignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ScriptReader {
    BufferedReader reader = new BufferedReader(new FileReader("SQL_Script_Table_Insert_Script.sql"));

    public ScriptReader() throws FileNotFoundException {

    }
}
