package Interfaces;

import Models.QuizzCategorie;

public interface QuizzCategory<T> {
   void addCategorie(QuizzCategorie categorie);
   void updateCategorie(QuizzCategorie categorie);
    void deleteQuizz(int id);

}
