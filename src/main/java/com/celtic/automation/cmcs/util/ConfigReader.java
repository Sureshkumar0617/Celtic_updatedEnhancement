package com.celtic.automation.cmcs.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {

	public static String URL;
	public  Properties prop;
	HashMap<String,String> hs= new HashMap<String,String>();
	String fileName="./src/test/resources/config/config.properties";
	 public static Logger log ;

	 
		public void loggerConfigurator(String classname) {
			String test = classname;
			String path =System.getProperty("user.dir")+"/log/"+test;
			System.setProperty("file.path",path);
			log = LogManager.getLogger(classname);
		}
	public  Properties initprop() throws IOException {
		prop = new Properties();
		FileInputStream ip = null ;
		try {
			ip	= new FileInputStream(fileName);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			log.error("Error in init_prop"+e);
		} catch (IOException e) {
			log.error("Error in init_prop"+e);
		}
		finally {
			ip.close();
		}
		return prop;
	}
	

	public   String readJson() throws IOException {
		return prop.getProperty("readjson");
	}

	public   String readBrowser() throws IOException {
		try
		{
			return prop.getProperty("browser");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}

	public   String readLoginURL() throws IOException {
		try{
			return prop.getProperty("loginURL");         
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}

	public   String readUserId() throws IOException {
		try{
			return prop.getProperty("userid");         
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public   String readPswrd() throws IOException {
		try{
			return prop.getProperty("pswrd");         
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readTestSuiteDirectory() throws Exception {
		try{
			return prop.getProperty("testSuiteDirectory");         
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readLoginEmpId() throws Exception {
		try
		{
			return prop.getProperty("LoginEmpId");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readAmendVehicleCondition() throws Exception {
		try
		{
			return prop.getProperty("AmendVehicleCondition");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readDeleteVehicleCondition() throws Exception {
		try
		{
			return prop.getProperty("DeleteVehicleCondition");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String getApplicationName() throws Exception {
		try
		{
			return prop.getProperty("ApplicationName");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public   String readJdbcDriver() throws Exception {
		try
		{
			return prop.getProperty("ApplicationName");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String getOS() throws Exception {
		try
		{
			return prop.getProperty("OS");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}

	public  String getEnvironment() throws Exception {
		try
		{
			return prop.getProperty("Environment");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}

	public  String writeRwcExcel() throws Exception {
		try
		{
			return prop.getProperty("writeRWCExcel");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}

	public   String writeRinExcel() throws Exception {
		try
		{
			return prop.getProperty("writeRINExcel");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public   String readRwcExcel() throws Exception {
		try
		{
			return prop.getProperty("RWCexcel");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}

	public  String readRINexcel() throws Exception {
		try
		{
			return prop.getProperty("RINexcel");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}

	public  String readLoginInternalUser() throws Exception {
		try
		{
			return prop.getProperty("LoginInternalUser");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}

	public  String readLoginServiceProvider1() throws Exception {
		try
		{
			return prop.getProperty("LoginServiceProvider1");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readLoginServiceProvider2() throws Exception {
		try
		{
			return prop.getProperty("LoginServiceProvider2");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readLoginExternaluser1() throws Exception {
		try
		{
			return prop.getProperty("LoginExternaluser1");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readLoginExternaluser2() throws Exception {
		try
		{
			return prop.getProperty("LoginExternaluser2");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readPassedScreenshotFile() throws Exception {
		try
		{
			return prop.getProperty("ScreenshotPass");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readDownloadFolder() throws Exception {
		try
		{
			return prop.getProperty("DownloadFolder");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}

}
