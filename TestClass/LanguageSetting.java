package TestClass;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageSetting {
	private static final int LANGUAGE_EN = 1;
	private static final int LANGUAGE_CN = 2;
	private static final int LANGUAGE_MM = 3;
	int currentLanguage;
	private ResourceBundle bundle;
	
	public LanguageSetting(){
		
	}
	
	public LanguageSetting(int currentLanguage){
		setCurrentLanguage(currentLanguage);
	}
	
	public void setResourceBundle(Locale locale){
		bundle = ResourceBundle.getBundle("resource/Main",locale);
	}
	public ResourceBundle getResourceBundle(){
		
		return bundle;
	}
	
	
	public String getWord(String key){
		if(bundle != null){
			return bundle.getString(key);
		}
		return "";
	}
	
	public void setCurrentLanguage(int currentLanguage){
		this.currentLanguage = currentLanguage;
		String language;
		switch(this.currentLanguage){
		case LANGUAGE_EN:
			setResourceBundle(new Locale("en","EN"));
			break;
		case LANGUAGE_CN:
			setResourceBundle(new Locale("cn","CN"));
			break;
		case LANGUAGE_MM:
			setResourceBundle(new Locale("mm","MM"));
			break;
		}
	}
	
	public String getCurrentLanguage(){
		switch(currentLanguage){
		case LANGUAGE_EN:
			return "English";
		case LANGUAGE_CN:
			return "Chinese";
		case LANGUAGE_MM:
			return "Burmese";
		}
		return "Wrong Choose Language";
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LanguageSetting setting = new LanguageSetting(LanguageSetting.LANGUAGE_CN);
		System.out.println(setting.getWord("Login.cardNo"));
		System.out.println(setting.getWord("Login.password"));
		setting.setCurrentLanguage(LANGUAGE_EN);
		System.out.println(setting.getWord("Cancel"));
		System.out.println(setting.getWord("Login.title"));
		

	}

}
