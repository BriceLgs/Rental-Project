# Rental API

## Description
Rental API est une application de gestion de locations immobilières. Elle permet aux utilisateurs de s'inscrire, de se connecter, de gérer leurs annonces de location, d'envoyer et de recevoir des messages, ainsi que de télécharger des images associées aux annonces.

## Fonctionnalités
- **Authentification** : Inscription et connexion des utilisateurs avec JWT.
- **Gestion des locations** : Création, récupération et affichage des annonces de location.
- **Gestion des messages** : Envoi et récupération des messages associés aux locations.
- **Téléchargement d'images** : Upload d'images pour les annonces de location.
- **Documentation API** : Documentation interactive via Swagger.

## Technologies utilisées
- **Backend** : Spring Boot
- **Base de données** : MySQL
- **Sécurité** : Spring Security avec JWT
- **Documentation** : Swagger

## Prérequis
- Java 11 ou supérieur
- Maven
- MySQL
- Node.js (pour le frontend si applicable)

## Installation

1. **Clonez le dépôt** :
   ```bash
   git clone https://github.com/USERNAME/mon-projet.git
   cd mon-projet
   ```

2. **Configurez la base de données** :
   - Créez une base de données MySQL nommée `rentalproject`.
   - Mettez à jour les informations de connexion dans `src/main/resources/application.properties`.

3. **Construisez le projet** :
   ```bash
   mvn clean install
   ```

4. **Démarrez l'application** :
   ```bash
   mvn spring-boot:run
   ```

5. **Accédez à l'API** :
   - L'API sera disponible à l'adresse `http://localhost:3001`.
   - La documentation Swagger sera accessible à `http://localhost:3001/swagger-ui.html`.

## Utilisation

# Instructions de Configuration de l'Application

Pour configurer et exécuter l'application, suivez les étapes ci-dessous :

## 1. Créer la Base de Données

Ouvrez votre client MySQL et exécutez la commande suivante pour créer la base de données :
sql
CREATE DATABASE rentalproject;

## 2. Configurer le Fichier `.env`

Créez un fichier nommé `.env` à la racine de votre projet (au même niveau que le dossier `src`).

Utilisez le modèle suivant pour remplir le fichier `.env` :

```
DATABASE_URL=jdbc:mysql://localhost:3306/rentalproject
DATABASE_USERNAME=your_username
DATABASE_PASSWORD=your_password
JWT_KEY=your_secret_key
```

Remplacez `your_username`, `your_password`, et `your_jwt_key` par vos informations d'identification et votre clé JWT.

## 3. Vérifier le Fichier `application.properties`

Assurez-vous que le fichier `src/main/resources/application.properties` contient les lignes suivantes :
properties
spring.datasource.name=rentalproject
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
server.port=3001
jwt.key=${JWT_KEY}

## 4. Démarrer l'Application

Compilez et exécutez l'application. Les tables nécessaires seront créées automatiquement dans la base de données si Hibernate est configuré correctement.

## 5. Accéder à l'API

L'application sera accessible sur `http://localhost:3001`. Vous pouvez utiliser des outils comme Postman pour interagir avec l'API.

## Remarques

- Assurez-vous que le driver JDBC pour MySQL est inclus dans votre projet.
- Si vous avez besoin d'aide supplémentaire, consultez la documentation de Spring Boot ou les fichiers de configuration.

### Authentification
- **Inscription** : `POST /api/auth/register`
- **Connexion** : `POST /api/auth/login`

### Gestion des locations
- **Lister les locations** : `GET /api/rentals`
- **Créer une location** : `POST /api/rentals`
- **Récupérer une location** : `GET /api/rentals/{id}`

### Gestion des messages
- **Envoyer un message** : `POST /api/messages`
- **Récupérer les messages d'une location** : `GET /api/messages/rental/{rentalId}`

## Contribuer
Les contributions sont les bienvenues ! N'hésitez pas à soumettre des demandes de tirage (pull requests) pour améliorer le projet.

## License
Ce projet est sous licence MIT. Voir le fichier LICENSE pour plus de détails.
