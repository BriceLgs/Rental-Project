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
