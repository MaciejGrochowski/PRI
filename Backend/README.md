### Jak odpalić projekt?

* Do działania aplikacji wymagana jest pusta  MySql'owa baza, o nazwie "ork" ustawiona na porcie 3306.  
W razie potrzeby connect do bazy można dostosować w pliku application.properties. 

#### Ewentualne problemy:  
* Niektóre adnotacje np. @Data, oraz gettery i settery świecą na czerwono?  
-> Proszę zainstalować Plugin "Lombok" z perspektywy inteliji, a następnie odświeżyć mavena. 

* Podczas budowania aplikacji pojawi się błąd  
" Some problems were encountered while building the effective model for com.example:PRI:jar:0.0.1-SNAPSHOT
'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: javax.xml.bind:jaxb-api:jar -> duplicate declaration of version 2.3.0 @ line 51, column 15
'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: mysql:mysql-connector-java:jar -> version 8.0.16 vs (?) @ line 62, column 15 [...]"  
-> Proszę zakomentować należy zakomentować dependencję "javax.xml.bind" w pom.xml  

