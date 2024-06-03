# Leaders
# EvoLearn Application

## Table of Contents
- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Database Setup](#database-setup)
- [Running the Application](#running-the-application)
- [Classes and Methods](#classes-and-methods)

## Introduction
EvoLearn is an online learning platform that allows users to create and follow courses, take quizzes, earn badges, and obtain certifications. This repository contains the source code and instructions for setting up the EvoLearn application.

## Prerequisites
- Java Development Kit
- MySQL Server
- MySQL phpMyAdmin for database management

## Database Setup
1. Start your MySQL server.
2. Create a new database called `evolearn`.
3. Use the following SQL scripts to create the necessary tables:

```sql
CREATE TABLE Utilisateur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    motDePasse VARCHAR(255) NOT NULL
);

CREATE TABLE Profil (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bio TEXT,
    photo VARCHAR(255),
    utilisateur_id INT,
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateur(id)
);

CREATE TABLE Entite (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE Cours (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entite_id INT,
    titre VARCHAR(255) NOT NULL,
    description TEXT,
    video VARCHAR(255),
    FOREIGN KEY (entite_id) REFERENCES Entite(id)
);

CREATE TABLE Quizz (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entite_id INT,
    titre VARCHAR(255) NOT NULL,
    description TEXT,
 date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    FOREIGN KEY (entite_id) REFERENCES Entite(id)
);

CREATE TABLE Inscription (
    id INT AUTO_INCREMENT PRIMARY KEY,
     date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    cours_id INT,
    utilisateur_id INT,
    FOREIGN KEY (cours_id) REFERENCES Cours(id),
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateur(id)
);

CREATE TABLE Formation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entite_id INT,
    titre VARCHAR(255) NOT NULL,
    description TEXT,
    FOREIGN KEY (entite_id) REFERENCES Entite(id)
);

CREATE TABLE Evenement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entite_id INT,
    titre VARCHAR(255) NOT NULL,
    description TEXT,
    FOREIGN KEY (entite_id) REFERENCES Entite(id)
);

CREATE TABLE Categorie (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    parent_id INT,
    FOREIGN KEY (parent_id) REFERENCES Categorie(id)
);

CREATE TABLE Badge (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL
);

CREATE TABLE Certif (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL
);

CREATE TABLE Article (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entite_id INT,
    titre VARCHAR(255) NOT NULL,
    description TEXT,
    contenu TEXT,
     date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    FOREIGN KEY (entite_id) REFERENCES Entite(id)
);

CREATE TABLE Cours_Utilisateur (
    cours_id INT,
    utilisateur_id INT,
    PRIMARY KEY (cours_id, utilisateur_id),
    FOREIGN KEY (cours_id) REFERENCES Cours(id),
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateur(id)
);

CREATE TABLE Utilisateur_Quizz (
    utilisateur_id INT,
    quizz_id INT,
    PRIMARY KEY (utilisateur_id, quizz_id),
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateur(id),
    FOREIGN KEY (quizz_id) REFERENCES Quizz(id)
);

CREATE TABLE Formation_Utilisateur (
    formation_id INT,
    utilisateur_id INT,
    PRIMARY KEY (formation_id, utilisateur_id),
    FOREIGN KEY (formation_id) REFERENCES Formation(id),
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateur(id)
);

CREATE TABLE Evenement_Utilisateur (
    evenement_id INT,
    utilisateur_id INT,
    PRIMARY KEY (evenement_id, utilisateur_id),
    FOREIGN KEY (evenement_id) REFERENCES Evenement(id),
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateur(id)
);

CREATE TABLE Quizz_Badge (
    quizz_id INT,
    badge_id INT,
    PRIMARY KEY (quizz_id, badge_id),
    FOREIGN KEY (quizz_id) REFERENCES Quizz(id),
    FOREIGN KEY (badge_id) REFERENCES Badge(id)
);

CREATE TABLE Cours_Certif (
    cours_id INT,
    certif_id INT,
    PRIMARY KEY (cours_id, certif_id),
    FOREIGN KEY (cours_id) REFERENCES Cours(id),
    FOREIGN KEY (certif_id) REFERENCES Certif(id)
);

CREATE TABLE Utilisateur_Article (
    utilisateur_id INT,
    article_id INT,
    PRIMARY KEY (utilisateur_id, article_id),
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateur(id),
    FOREIGN KEY (article_id) REFERENCES Article(id)
);

CREATE TABLE Cours_Categorie (
    cours_id INT,
    categorie_id INT,
    PRIMARY KEY (cours_id, categorie_id),
    FOREIGN KEY (cours_id) REFERENCES Cours(id),
    FOREIGN KEY (categorie_id) REFERENCES Categorie(id)
);

CREATE TABLE Formation_Categorie (
    formation_id INT,
    categorie_id INT,
    PRIMARY KEY (formation_id, categorie_id),
    FOREIGN KEY (formation_id) REFERENCES Formation(id),
    FOREIGN KEY (categorie_id) REFERENCES Categorie(id)
);

CREATE TABLE Ressources (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    description TEXT,
    url VARCHAR(255),
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE Temoignages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    utilisateur_id INT,
    contenu TEXT,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateur(id)
);
CREATE TABLE Communaute (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    description TEXT,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
