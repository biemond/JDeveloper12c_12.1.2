RestFulOWSM
===========

JDeveloper RestFul / JPA / EJB project entirely build and deployed with Oracle maven plugins.  
[requires a maven reposity with Oracle artifacts](http://docs.oracle.com/middleware/1212/core/MAVEN/config_maven.htm#MAVEN8853) 


Model project
-------------

EJB/JPA based on the Oracle HR Demo schema

ModelTest project
-----------------

JUnit 4 JPA tests which are invoke by the pom of the model project

webService project
------------------

JAX-RS RestFul services


EAR
---

Generate an EAR and deploys to the internal JDeveloper server. 

 
my maven env script
-------------------

    export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_21.jdk/Contents/Home/jre                
    export M2_HOME=/Users/edwin/Oracle/JDevMiddleware12.1.2/oracle_common/modules/org.apache.maven_3.0.4
    export PATH=${M2_HOME}/bin:${JAVA_HOME}:$PATH                                                       
    mvn -v


my maven settings.xml
---------------------

I use artifactory and uploaded all oracle jars to this shared repository.



    <?xml version="1.0" encoding="UTF-8"?>
    
    <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" 
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
              xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
    
      <pluginGroups>
      </pluginGroups>
    
      <proxies>
      </proxies>
    
      <servers>
      	<server>
          <id>central</id>
          <username>admin</username>
          <password>password</password>
        </server>
      	<server>
          <id>snapshots</id>
          <username>admin</username>
          <password>password</password>
        </server>
        <server>
          <id>artifactory</id>
          <username>admin</username>
          <password>password</password>
        </server>
        <server>
          <id>internal</id>
          <username>admin</username>
          <password>password</password>
        </server>
      </servers>
    
      <mirrors>
        <mirror>
          <id>artifactory</id>
          <name>Internal artifactory Mirror of Central</name>
          <url>http://hudson:8081/artifactory/repo</url>
          <mirrorOf>*</mirrorOf>
        </mirror>
        
      </mirrors>
      
      <profiles>
    
        <profile>
          <id>artifactory</id>
          <repositories>
            <repository>
              <snapshots>
                <enabled>false</enabled>
              </snapshots>
              <id>central</id>
              <name>libs-release</name>
              <url>http://hudson:8081/artifactory/libs-release</url>
            </repository>
            <repository>
              <snapshots />
              <id>snapshots</id>
              <name>libs-snapshot</name>
              <url>http://hudson:8081/artifactory/libs-snapshot</url>
            </repository>
            <repository>
              <snapshots />
              <id>internal</id>
              <name>libs-snapshot</name>
              <url>http://hudson:8081/artifactory/ext-release-local</url>
            </repository>
          </repositories>
          <pluginRepositories>
            <pluginRepository>
              <snapshots>
                <enabled>false</enabled>
              </snapshots>
              <id>central</id>
              <name>plugins-release</name>
              <url>http://hudson:8081/artifactory/plugins-release</url>
            </pluginRepository>
            <pluginRepository>
              <snapshots />
              <id>snapshots</id>
              <name>plugins-snapshot</name>
              <url>http://hudson:8081/artifactory/plugins-snapshot</url>
            </pluginRepository>
          </pluginRepositories>
        </profile>
    
        <profile>
          <id>oracle-maven</id>
          <properties>
            <oracle-maven-sync.serverId>internal</oracle-maven-sync.serverId>
            <oracle-maven-sync.oracleHome>/Users/edwin/Oracle/JDevMiddleware12.1.2</oracle-maven-sync.oracleHome>
            <oracle-maven-sync.testOnly>false</oracle-maven-sync.testOnly>
            <oracle-maven-sync.failOnError>false</oracle-maven-sync.failOnError>
          </properties>      
        </profile>  
    
    
    
      </profiles>
    
      <activeProfiles>
         <activeProfile>artifactory</activeProfile>
         <activeProfile>oracle-maven</activeProfile>
      </activeProfiles>
    
    </settings>
      