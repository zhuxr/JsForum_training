
package forum;

public class FilterBack {
	
	
    public static String filterAll(String string)
    {	
		string = filterBreak(string);
		string = filterTextStyle(string);
		//string = filterImage(string);
		//string = filterURL(string);
		string = filterEmoticons(string);
		
		return string;
	}
	
    public static String filterBreak(String string)
    {
		if(string == null || string.length() < 1)
        {
            return string;
        }
        
        StringBuffer source = new StringBuffer(string);
        
        for(int i = 0; i < source.length(); i++)
        {
            if(source.charAt(i) == '<')
            {
            	if(source.charAt(i+1) == 'b' && source.charAt(i+2) =='r' && source.charAt(i+3) =='>')
            	{	
                	source.replace(i,i+4,"\n");
                }
            } 
        }
        return source.toString();
	}

	public static String filterTextStyle(String string)
	{
        if(string == null || string.length() < 1)
        {
            return string;
        }

        StringBuffer source = new StringBuffer(string);
        
        char charArray[] = {'B','b','I','i','U','u','S','s'};
        
        for(int i = 0; i < source.length(); i++)
        {
            if(source.charAt(i) == '<' && source.charAt(i+2) == '>')
            {
                for(int j = 0; j < charArray.length; j++)
            	{
	                if(source.charAt(i+1) == charArray[j])
	                {
	                	source.replace(i,i+3,"["+ charArray[j] +"]"); 
	                }
	            }
            }
            else if(source.charAt(i) == '<' &&  source.charAt(i+1) == '/' && source.charAt(i+3) == '>')
            {
            	for(int j = 0; j < charArray.length; j++)
            	{
	            	if(source.charAt(i+2) == charArray[j])
	            	{
	                	source.replace(i,i+4,"[/"+ charArray[j] +"]"); 
	                }
	            }
            }
		}
		return source.toString();
	}

	public static String filterImage(String string)
	{
        if(string == null || string.length() < 1)
        {
            return string;
        }
        
		StringBuffer source = new StringBuffer( string );
    
        for(int i = 0; i < source.length(); i++)
        {
            if(source.charAt(i) == '<')
            {
            	for( int j = i+1;j < (source.length());j++)
            	{
            		if(source.charAt(j) == '>')
            		{
            			String subString = source.substring(i+1,i+8);
						if(subString.equals("img src="))
						{
							source.replace(i,j+1,"[IMG]");
							source.replace(j,j+1,"[/IMG]");
						}
            		}	
            	}

            }
        }


		return  source.toString();
	}

	public static String filterURL(String string)
	{
        if(string == null || string.length() < 1)
        {
            return string;
        }
        
		StringBuffer source = new StringBuffer( string );
    
        for(int i = 0; i < source.length(); i++)
        {
            if(source.charAt(i) == '<')
            {
            	for( int j = i+1;j < source.length();j++)
            	{
            		if(source.charAt(j) == '>')
            		{
            			
						if(source.substring(i+1,i+8).equals("a href="))
						{
							source.replace(i,i+8,"[URL=");
							source.replace(j-9,j-2,"]");
							//source.append("|ok");
							
						}else if(source.substring(i+1,j).equals("/a"))
						{
							source.replace(i,j+1,"[/URL]");	
							
						}else{
							source.append("|12345678");
							//source.append("|" + source.substring(i+1,i+2));	
						}
            		}	
            	}

            }
        }
		return  source.toString();
	}
	
	public static String filterEmoticons(String string)
	{	
        StringBuffer source = new StringBuffer( string );
    
        for(int i = 0; i < source.length(); i++){          
         	if(source.charAt(i) == '<'){
        		for( int j = i+1;j < (source.length());j++){        			
        			if(source.charAt(j) == '>'){
        				String subString = source.substring(i+1,j);
						if(subString.equals("img src=./emoticons/mellow.gif border=0")){
							source.replace(i,j+1,":mellow:");
						}else if(subString.equals("img src=./emoticons/huh.gif border=0")){
							source.replace(i,j+1,":huh:");
						}else if(subString.equals("img src=./emoticons/rolleyes.gif border=0")){
							source.replace(i,j+1,":rolleyes:");
						}else if(subString.equals("img src=./emoticons/lol.gif border=0")){
							source.replace(i,j+1,":lol:");
						}else if(subString.equals("img src=./emoticons/wub.gif border=0")){
							source.replace(i,j+1,":wub:");
						}else if(subString.equals("img src=./emoticons/angry.gif border=0")){
							source.replace(i,j+1,":angry:");
						}else if(subString.equals("img src=./emoticons/unsure.gif border=0")){
							source.replace(i,j+1,":unsure:");
						}else if(subString.equals("img src=./emoticons/wacko.gif border=0")){
							source.replace(i,j+1,":wacko:");
						}else if(subString.equals("img src=./emoticons/blink.gif border=0")){
							source.replace(i,j+1,":blink:");
						}else if(subString.equals("img src=./emoticons/ph34r.gif border=0")){
							source.replace(i,j+1,":ph34r:");
						}else if(subString.equals("img src=./emoticons/bounce.gif border=0")){
							source.replace(i,j+1,":bounce:");
						}else if(subString.equals("img src=./emoticons/fuck.gif border=0")){
							source.replace(i,j+1,":fuck:");
						}else if(subString.equals("img src=./emoticons/scream.gif border=0")){
							source.replace(i,j+1,":scream.gif:");
						}else if(subString.equals("img src=./emoticons/silly.gif border=0")){
							source.replace(i,j+1,":silly:");
						}else if(subString.equals("img src=./emoticons/frusty.gif border=0")){
							source.replace(i,j+1,":frusty:");
						}else if(subString.equals("img src=./emoticons/kwijl.gif border=0")){
							source.replace(i,j+1,":kwijl:");
						}else if(subString.equals("img src=./emoticons/puke.gif border=0")){
							source.replace(i,j+1,":puke:");
						}else if(subString.equals("img src=./emoticons/bid.gif border=0")){
							source.replace(i,j+1,":bid:");
						}else if(subString.equals("img src=./emoticons/hammer.gif border=0")){
							source.replace(i,j+1,":hammer:");
						}else if(subString.equals("img src=./emoticons/good.gif border=0")){
							source.replace(i,j+1,":good:");	
						}												
        			}
        		}
        		
			}   	
    	}
    	return source.toString();
    }
	
	
}