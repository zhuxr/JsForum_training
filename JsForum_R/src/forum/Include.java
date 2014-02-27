package forum;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class Include {
  
  public static final String DOCTYPE =
    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
    "Transitional//EN\">";
  
  public static String header(String title){
   	String head =	
   		"<Html>\n"+
  		"<Head>\n"+
  		"<Title>"+ title + "</Title>\n"+
  		"</Head>\n";
    return(DOCTYPE + "\n" + head ); 
  }

  public static String body(){
  	String body =
  		"<Body>\n"+
  		"<center><a href=\"..\\forum\\index.jsp\"><img src=\"..\\forum\\logo.jpg\" border=\"0\"></a></center>\n"+
  		"<br>\n";

  	return(body);
  }
   
  public static String body(String username){
  	String body =
  		Table_start()+
  		Table_title("InfoBar",1,"left")+
  		Table_start_body()+
 		Table_body(new Date().toString(),0)+
 		Table_close_body()+	 
        Table_close()+
  		"<Body>\n"+
  		"<center><a href=\"..\\forum\\index.jsp\"><img src=\"..\\logo.jpg\" border=\"0\"></a></center>\n"+
  		"<br>\n"+
  		Table_start()+
 		Table_title("ToolBar",1,"left")+
 		Table_start_body()+
 		Table_body(
 			"Logged in as:<b> "+ username.toUpperCase() + "</b>&nbsp;&nbsp;&nbsp;"+
            "<a href=\"forum.Profile\">Your Profile</a>",0)+
 		Table_close_body()+	 
        Table_close()+
        "<br>\n";

  	return(body);
  }
  
  public static String Table_start(){
  	String Table_start =
  		"<TABLE cellSpacing=\"0\" cellPadding=\"0\" width=\"90%\" bgColor=\"#336699\" border=\"0\" align=\"center\">\n" +
        "<TR>\n"+
        "<TD>\n"+
        "<TABLE cellSpacing=\"1\" cellPadding=\"4\" width=\"100%\" border=\"0\">\n"+
        "<TR bgColor=\"#ccccff\">\n";
  	return Table_start;
  }
  
  public static String Table_start_title(){
  	String Table_start_title ="<TR bgColor=#ccccff>";
  	return Table_start_title;
  }
   
  public static String Table_title(String title,int colspan,String align){
  	String Table_title=
  		"<TD align=\"" + align + "\" colspan=\"" + colspan + "\">"+
  		"<FONT face=Verdana,Arial,Helvetica color=#003366 size=1>"+
  		"<B>"+ title + "</B>"+
  		"</FONT>" +
  		"</TD>";
	return Table_title;
  }
  
  public static String Table_start_body(){
  	String Table_start_body="<TR bgColor=#ffffff>";
  	return Table_start_body;
  }
  public static String Table_body(String body,int width){
  	String Table_body=
        "<TD align=\"left\" valign=\"top\" width=\"" + width +"\">" +
        "<FONT face=Verdana,Arial,Helvetica color=#003366 size=1>" + body + 
        "</FONT>"+
        "</TD>";
	return Table_body;
  } 
  
  public static String Table_close_body(){
  	String Table_close_body="</TR>";
  	return Table_close_body;
  }
  
  public static String Table_close(){
  	String Table_close=
  		"</TABLE>"+
		"</TD>"+
		"</TR>"+
		"</TABLE>";
  	return Table_close;
  }
  
  public static String emoticons(){
  	String emoticons =
  		"<table cellSpacing=\"0\" cellPadding=\"5\" width=\"100%\">\n"+
  		"<tr>\n"+
  		"<td width=100><a onclick=javascript:form.message.value+=\":mellow:\";form.message.focus();><img src=./emoticons/mellow.gif border=0></a></td>\n" +
  		"<td width=100><a onclick=javascript:form.message.value+=\":huh:\";form.message.focus();><img src=./emoticons/huh.gif border=0></a></td>\n" +
  		"<td width=100><a onclick=javascript:form.message.value+=\":rolleyes:\";form.message.focus();><img src=./emoticons/rolleyes.gif border=0></a></td>\n" +
  		"</tr>\n"+
  		"<tr>\n"+
  		"<td width=100><a onclick=javascript:form.message.value+=\":lol:\";form.message.focus();><img src=./emoticons/lol.gif border=0></a></td>\n" +
  		"<td width=100><a onclick=javascript:form.message.value+=\":wub:\";form.message.focus();><img src=./emoticons/wub.gif border=0></a></td>\n" +
  		"<td width=100><a onclick=javascript:form.message.value+=\":angry:\";form.message.focus();><img src=./emoticons/angry.gif border=0></a></td>\n" +
  		"</tr>\n"+
  		"<tr>\n"+
  		"<td width=100><a onclick=javascript:form.message.value+=\":unsure:\";form.message.focus();><img src=./emoticons/unsure.gif border=0></a></td>\n" +
  		"<td width=100><a onclick=javascript:form.message.value+=\":wacko:\";form.message.focus();><img src=./emoticons/wacko.gif border=0></a></td>\n" +
  		"<td width=100><a onclick=javascript:form.message.value+=\":blink:\";form.message.focus();><img src=./emoticons/blink.gif border=0></a></td>\n" +
  		"</tr>\n"+
  		"<tr>\n"+
  		"<td width=100><a onclick=javascript:form.message.value+=\":ph34r:\";form.message.focus();><img src=./emoticons/ph34r.gif border=0></a></td>\n" +
  		"<td width=100><a onclick=javascript:form.message.value+=\":wacko:\";form.message.focus();><img src=./emoticons/wacko.gif border=0></a></td>\n" +
  		"<td width=100><a onclick=javascript:form.message.value+=\":bounce:\";form.message.focus();><img src=./emoticons/bounce.gif border=0></a></td>\n" +
  		"</tr>\n"+
  		"<tr>\n"+
  		"<td width=100><a onclick=javascript:form.message.value+=\":fuck:\";form.message.focus();><img src=./emoticons/fuck.gif border=0></a></td>\n" +
  		"<td width=100><a onclick=javascript:form.message.value+=\":scream:\";form.message.focus();><img src=./emoticons/scream.gif border=0></a></td>\n" +
  		"<td width=100><a onclick=javascript:form.message.value+=\":silly:\";form.message.focus();><img src=./emoticons/silly.gif border=0></a></td>\n" +
  		"</tr>\n"+
  		"<tr>\n"+
  		"<td width=100><a onclick=javascript:form.message.value+=\":frusty:\";form.message.focus();><img src=./emoticons/frusty.gif border=0></a></td>\n" +
  		"<td width=100><a onclick=javascript:form.message.value+=\":kwijl:\";form.message.focus();><img src=./emoticons/kwijl.gif border=0></a></td>\n" +
  		"<td width=100><a onclick=javascript:form.message.value+=\":puke:\";form.message.focus();><img src=./emoticons/puke.gif border=0></a></td>\n" +
  		"</tr>\n"+
  		"<tr>\n"+
  		"<td width=100><a onclick=javascript:form.message.value+=\":bid:\";form.message.focus();><img src=./emoticons/bid.gif border=0></a></td>\n" +
  		"<td width=100><a onclick=javascript:form.message.value+=\":hammer:\";form.message.focus();><img src=./emoticons/hammer.gif border=0></a></td>\n" +
  		"<td width=100><a onclick=javascript:form.message.value+=\":good:\";form.message.focus();><img src=./emoticons/good.gif border=0></a></td>\n" +
  		"</tr></table>\n";
  	return emoticons;
  }
  
  
  public static String avatars(){											
  	String avatars = "<select name=\"avatar_list\" size=\"10\" onchange=\"document.images.img.src=document.profile.avatar_list.options[document.profile.avatar_list.selectedIndex].value;document.profile.link_avatar.value=document.profile.avatar_list.options[document.profile.avatar_list.selectedIndex].value;\">\n";
	for(int i=0;i<55;i++){
		avatars = avatars + "<option value=\"../avatars/avatar_" + i + ".jpg\">Avatar " + i + "</option>\n";
	}
  	avatars = avatars + "</select>\n";
  	return avatars;	
  }
  
  public static String footer(){
  	String foot =
  		"<br><center>&copy; 2002 by <a href=mailto:grieks03@ie.hva.nl>Erwin</a></center>"+
  		"</Body>\n"+
  		"</Html>\n";
  	return(foot);
  }
}