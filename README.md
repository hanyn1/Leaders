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
CREATE TABLE Utilisateurs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    motDePasse VARCHAR(255) NOT NULL
);

CREATE TABLE Profils (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bio TEXT,
    photo VARCHAR(255),
    utilisateur_id INT,
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateurs(id)
);

CREATE TABLE Entites (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE Cours (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    description TEXT,
    video VARCHAR(255),
    image VARCHAR(255) NOT NULL,
    price FLOAT,
    expirationDate DATE,
    isActive BOOLEAN DEFAULT TRUE
);


CREATE TABLE Quizzs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    description TEXT,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quizz_id INT,
    question_text TEXT NOT NULL,
    FOREIGN KEY (quizz_id) REFERENCES Quizzs(id)
);

CREATE TABLE Options (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question_id INT,
    option_text TEXT NOT NULL,
    is_correct BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (question_id) REFERENCES Questions(id)
);

CREATE TABLE Answers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    question_id INT,
    selected_option INT,
    FOREIGN KEY (question_id) REFERENCES Questions(id),
    FOREIGN KEY (selected_option) REFERENCES Options(id)
);

CREATE TABLE inscriptions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cours_id INT NOT NULL,
    utilisateur_id INT NOT NULL,
    expirationDate TIMESTAMP NOT NULL,
    FOREIGN KEY (cours_id) REFERENCES Cours(id),
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateurs(id)
);

CREATE TABLE Formations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE Evenements (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE Categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    parent_id INT,
    FOREIGN KEY (parent_id) REFERENCES Categories(id)
);

CREATE TABLE Badges (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL
);

CREATE TABLE Certifs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL
);

CREATE TABLE Articles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    description TEXT,
    contenu TEXT,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMPid)
);

CREATE TABLE Cours_Utilisateurs (
    cours_id INT,
    utilisateur_id INT,
    PRIMARY KEY (cours_id, utilisateur_id),
    FOREIGN KEY (cours_id) REFERENCES Cours(id),
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateurs(id)
);

CREATE TABLE Utilisateurs_Quizzs (
    utilisateur_id INT,
    quizz_id INT,
    PRIMARY KEY (utilisateur_id, quizz_id),
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateurs(id),
    FOREIGN KEY (quizz_id) REFERENCES Quizzs(id)
);

CREATE TABLE Formations_Utilisateurs (
    formation_id INT,
    utilisateur_id INT,
    PRIMARY KEY (formation_id, utilisateur_id),
    FOREIGN KEY (formation_id) REFERENCES Formations(id),
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateurs(id)
);

CREATE TABLE Evenements_Utilisateurs (
    evenement_id INT,
    utilisateur_id INT,
    PRIMARY KEY (evenement_id, utilisateur_id),
    FOREIGN KEY (evenement_id) REFERENCES Evenements(id),
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateurs(id)
);

CREATE TABLE Quizzs_Badges (
    quizz_id INT,
    badge_id INT,
    PRIMARY KEY (quizz_id, badge_id),
    FOREIGN KEY (quizz_id) REFERENCES Quizzs(id),
    FOREIGN KEY (badge_id) REFERENCES Badges(id)
);

CREATE TABLE Cours_Certifs (
    cours_id INT,
    certif_id INT,
    PRIMARY KEY (cours_id, certif_id),
    FOREIGN KEY (cours_id) REFERENCES Cours(id),
    FOREIGN KEY (certif_id) REFERENCES Certifs(id)
);

CREATE TABLE Utilisateurs_Articles (
    utilisateur_id INT,
    article_id INT,
    PRIMARY KEY (utilisateur_id, article_id),
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateurs(id),
    FOREIGN KEY (article_id) REFERENCES Articles(id)
);

CREATE TABLE Cours_Categories (
    cours_id INT,
    categorie_id INT,
    PRIMARY KEY (cours_id, categorie_id),
    FOREIGN KEY (cours_id) REFERENCES Cours(id),
    FOREIGN KEY (categorie_id) REFERENCES Categories(id)
);

CREATE TABLE Formations_Categories (
    formation_id INT,
    categorie_id INT,
    PRIMARY KEY (formation_id, categorie_id),
    FOREIGN KEY (formation_id) REFERENCES Formations(id),
    FOREIGN KEY (categorie_id) REFERENCES Categories(id)
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
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateurs(id)
);

CREATE TABLE Communautes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    description TEXT,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
