The task is:

```
Configure the Tomcat and Apache integration with mod_jk.so module.
Build multi-module web application and deploy with tomcat manager application (text/script mode). 
Static (html, css, js) publish to apache, dynamic to tomcat.  Test and write readme, how mentor can deploy it and check that it is working.
```

The simple app to deploy on Tomcat and Apache WebServer as server for the static content.

Requirements:

- Java8
- Apache WebServer 2.4+
- Tomcat 8+


If you deployed app before, undeaploy it first:

```
http://localhost:8080/manager/text/undeploy?path=/module11
```

Command to deply app (Tomcat should be started before. Replace path to app WAR file to yours):

```
http://localhost:8080/manager/text/deploy?path=/module11&war=file:/media/d/projects/jmp2016/module12/module11.war
```

Configure Apache to use mod_jk and paths to TomCat.
Configuration for the virtual host of Apache (replace "/media/d/linux/apache-tomcat-8.0.23/webapps" to the path where module11.war has been deployed):

```
<VirtualHost *:80>
	ServerAdmin webmaster@localhost
	ServerName localhost
	DocumentRoot /media/d/linux/apache-tomcat-8.0.23/webapps
	ErrorLog ${APACHE_LOG_DIR}/error.log
	CustomLog ${APACHE_LOG_DIR}/access.log combined
  	JkMount /module11/* ajp13_worker
  	JkMount / ajp13_worker
  	JkAutoAlias /media/d/linux/apache-tomcat-8.0.23/webapps
    <Directory /media/d/linux/apache-tomcat-8.0.23/webapps/ >
        Options Indexes FollowSymLinks MultiViews
        AuthType None
        AllowOverride All
	Require all granted
    </Directory>
</VirtualHost>
```

Include mod_jk module to main Apache config (normaly, in ${APACHE_HOME}/conf/httpd.conf. Replace "/usr/lib/apache2/modules/mod_jk.so" to module where it is located on your Apache installation)

```
LoadModule jk_module /usr/lib/apache2/modules/mod_jk.so
```

Mod_jk workers properties file (Configuration for AJP protocol, path to Java and Tomcat home dir)

```
workers.tomcat_home=/media/d/linux/apache-tomcat-8.0.23
workers.java_home=/usr/lib/jvm/default-java
# You should configure your environment slash... ps=\ on NT and / on UNIX
# and maybe something different elsewhere.
ps=/
worker.list=ajp13_worker
worker.ajp13_worker.port=8009
worker.ajp13_worker.host=localhost
worker.ajp13_worker.type=ajp13
worker.ajp13_worker.lbfactor=1
worker.loadbalancer.type=lb
worker.loadbalancer.balance_workers=ajp13_worker
```


Mod_jk config (replace paths to logs to appropriate on your system and path to workers.properties from the previous point). Do not forget to include it to loading by Apache config (e.g., via "IncludeOptional /path/to/mod_jk_config/jk.conf" in httpd.conf)!
```
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
<IfModule jk_module>

    # We need a workers file exactly once
    # and in the global server
    JkWorkersFile /etc/libapache2-mod-jk/workers.properties

    # Our JK error log
    # You can (and should) use rotatelogs here
    JkLogFile /var/log/apache2/mod_jk.log

    # Our JK log level (trace,debug,info,warn,error)
    JkLogLevel info

    # Our JK shared memory file
    JkShmFile /var/log/apache2/jk-runtime-status

    # Define a new log format you can use in any CustomLog in order
    # to add mod_jk specific information to your access log.
    # LogFormat "%h %l %u %t \"%r\" %>s %b \"%{Referer}i\" \"%{User-Agent}i\" \"%{Cookie}i\" \"%{Set-Cookie}o\" %{pid}P %{tid}P %{JK_LB_FIRST_NAME}n %{JK_LB_LAST_NAME}n ACC %{JK_LB_LAST_ACCESSED}n ERR %{JK_LB_LAST_ERRORS}n BSY %{JK_LB_LAST_BUSY}n %{JK_LB_LAST_STATE}n %D" extended_jk

    # This option will reject all requests, which contain an
    # encoded percent sign (%25) or backslash (%5C) in the URL
    # If you are sure, that your webapp doesn't use such
    # URLs, enable the option to prevent double encoding attacks.
    # Since: 1.2.24
    # JkOptions +RejectUnsafeURI

    # This option will collapse multiple adjacent slashes
    # in request URLs before looking for mount or unmount
    # matches.
    # Since: 1.2.41
    # JkOptions +CollapseSlashesAll

    # After setting JkStripSession to "On", mod_jk will
    # strip all ";jsessionid=..." from request URLs it
    # does *not* forward to a backend.
    # This is useful, if all links in a webapp use
    # URLencoded session IDs and parts of the static
    # content should be delivered directly by Apache.
    # Of course you can also do it with mod_rewrite.
    # Since: 1.2.21
    # JkStripSession On

    # Start a separate thread for internal tasks like
    # idle connection probing, connection pool resizing
    # and load value decay.
    # Run these tasks every JkWatchdogInterval seconds.
    # Since: 1.2.27
    JkWatchdogInterval 60

    # Configure access to jk-status and jk-manager
    # If you want to make this available in a virtual host,
    # either move this block into the virtual host
    # or copy it logically there by including "JkMountCopy On"
    # in the virtual host.
    # Add an appropriate authentication method here!
    <Location /jk-status>
        # Inside Location we can omit the URL in JkMount
        JkMount jk-status
        Require all granted
    </Location>
    <Location /jk-manager>
        # Inside Location we can omit the URL in JkMount
        JkMount jk-manager
        Require all granted
    </Location>

</IfModule>
```

That's it for Apache configs.

For Tomcat config please make sure, that AJP support is enabled in ${CATALINA_HOME}/conf/server.xml. The following element shouldn't be commented:

```
<Connector port="8009" protocol="AJP/1.3" redirectPort="8443" />
```

Configuration is done. Start / Restart Tomcat and Apache and navigate to http://localhost/module11 

Have fun! :)
