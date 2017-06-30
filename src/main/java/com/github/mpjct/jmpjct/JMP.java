package com.github.mpjct.jmpjct;

/*
 * Java Mysql Proxy
 * Main binary. Just listen for connections and pass them over
 * to the proxy module
 */

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JMP {
    public static Properties config = new Properties();
    
    public static void main(String[] args) throws IOException {
        InputStream config = JMP.class.getResourceAsStream("/jmp.properties");
        JMP.config.load(config);
        config.close();
        
        Logger logger = Logger.getLogger("JMP");
        PropertyConfigurator.configure(JMP.config.getProperty("logConf").trim());
        
        String[] ports = JMP.config.getProperty("ports").split(",");
        for (String port: ports) {
            new JMP_Thread(Integer.parseInt(port.trim())).run();
        }
    }
}
