# Création d’un logiciel de gestion d’établissement scolaire

## Ennoncer

L’ENSITECH souhaite créer un logiciel sur mesure de gestion de l’école. Ce logiciel destiné à la direction permettra de mieux gérer la scolarité des étudiants, mais aussi les modules de formation, le réseau des enseignants et les cours.<br/>

Un étudiant est caractérisé par :
- Un identifiant unique
- Un nom
- Un prénom
- Une adresse email
- Une adresse
- Un numéro de téléphone
- Une date de naissance

Un enseignant est caractérisé par :
- Un identifiant unique
- Un nom
- Un prénom
- Une adresse email
- Une adresse
- Un numéro de téléphone
- Une matière enseignée

L’école ENSITECH est caractérisée par :
- Un nom
- Une adresse email
- Une adresse
- Un numéro de téléphone
- Un directeur

Un cours est caractérisé par :
- Thème du cours
- Nombre d’heures

Dans sa version 1, le logiciel permet au responsable des études ou au directeur de
l’école de
- Créer un Étudiant,
- Associer un cours à un étudiant
- Lire les informations d’un étudiant,
- Modifier une des informations sur l’étudiant,
- Supprimer un étudiant,
- Lister l’ensemble des étudiants de l’école

L’accès au logiciel est sécurisé. Seuls le directeur et le responsable des études
pourront utiliser le logiciel dans un premier temps. Toutes les fonctionnalités seront
accessibles une fois que chaque utilisateur se sera authentifié.<br/>

Le logiciel présente les fonctionnalités sous forme d’un menu. La fonctionnalité de listing des étudiants est accessible uniquement au Directeur.<br/>

## Livraison

### Conception
- diagramme Cas d’utilisation
- diagramme de classe
- diagrammes de séquence
- diagrammes d'activité
[Lien vers une page pour visualiser les diagrammes réalisé](./diagrammes/README.md)

### Développement et Test
Mettre en oeuvre l’écran de création d’un étudiant. Technologie Spring + Bootstrap + MySql
Couverture de test de 80%
[Lien vers le projet réaliser ](./GestionEtablissement/README.md)

### Devops et Déploiement
Devops : Docker, Jenkins, Ansible.
Déploiement : Amazon AWS

[Lien vers la partie devops réalisé](./devops/README.md)