/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifi.gde.base.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;
//import sun.misc.IOUtils;

/**
 *
 * @author User
 */
public class FileDAO implements IFileDAO{
    public void save(InputStream inputStream, File file) throws FileNotFoundException, IOException{
        OutputStream output = new FileOutputStream(file);
        IOUtils.copy(inputStream, output);
//        IOUtil.copy(inputStream, outputStream);
    }
}
