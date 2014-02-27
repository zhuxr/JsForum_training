###############################################################
#                                                             #  
#		  .:JSP/SERVLET SQL SCRIPT:.		      #
#                                                             #
# 	     usage: mysql databasename < forum.sql            #
#                                                             #
#                      (c) by erwin                           #
#                                                             #
###############################################################

CREATE TABLE forum_forums
(id 		int(10)		NOT NULL	auto_increment,  
 forum_id 	int(10)     	NOT NULL,
 title 		text 		NOT NULL,  
 forum_info 	text 		NOT NULL, 
 PRIMARY KEY (id,forum_id)
);

CREATE TABLE forum_message 
(id 		int(10)		NOT NULL	auto_increment, 
 forum_id 	int(10)		NOT NULL,
 thread_id 	int(10)		NOT NULL, 
 reply_id 	int(10)		NOT NULL, 
 message 	text		NOT NULL,  
 user 		text		NOT NULL,  
 date_time 	datetime 	NOT NULL,
 PRIMARY KEY (id,forum_id,thread_id,reply_id)
);

CREATE TABLE forum_threads 
(id 		int(10) 	NOT NULL	auto_increment,  
 forum_id 	int(10) 	NOT NULL,    
 thread_id 	int(10) 	NOT NULL,   
 title 		text 		NOT NULL,  
 views 		int(10) 	NOT NULL,
 PRIMARY KEY (id,forum_id,thread_id) 
);

CREATE TABLE forum_users
(id		int(10)		NOT NULL	auto_increment,
 user_name	text		NOT NULL,	
 password	text		NOT NULL,
 email		text		NOT NULL,
 registerdate	datetime	NOT NULL,
 avatar		text		NOT NULL,
 member_title	text		NOT NULL,
 signature	text		NOT NULL,
 PRIMARY KEY (id)
);

CREATE TABLE forum_settings
(id		int(10)		NOT NULL	auto_increment,
 dbName		text		NOT NULL,	
 dbLogin	text		NOT NULL,
 dbPassword	text		NOT NULL,
 forumPath	text		NOT NULL,
 forumName	text		NOT NULL,
 messagePerPage text		NOT NULL,
 PRIMARY KEY (id)
);