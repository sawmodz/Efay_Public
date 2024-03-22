# Efay project

## Information

Ce projet a été réalisé dans le cadre d'un projet de [groupe à l'école ENI](https://www.eni-ecole.fr).
**Développeur: Wincke Théo, Antoine Lemarchand, Ismael-Andrick Lundu-Monsu**
Vous pouvez trouver le site à l'adresse suivante: [lien vers le site](http://efay.blizzfull.fr)

## Environement

voici un exemple du fichier _application.properties_

```json
spring.application.name=Efay

#Internationalization = i18n
#Message bundles encoding
spring.messages.encoding=UTF-8

##Connexion a la base de donnees
spring.datasource.url=jdbc:mariadb://url:3306/database
spring.datasource.username=username
spring.datasource.password=password

### Configuration des pilotes JDBC
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver


#Detail des requetes pour le developpement
logging.level.org.springframework.jdbc.core=TRACE
logging.level.sql=debug

#Configuration de l'upload de fichier
spring.servlet.multipart.max-file-size=32MB
spring.servlet.multipart.max-request-size=32MB
```

## Contribution

Toute contribution est la bienvenue ! N'hésitez pas à apporter vos idées, suggestions et corrections en soumettant une demande de pull. Nous apprécions votre participation à l'amélioration de ce projet.
