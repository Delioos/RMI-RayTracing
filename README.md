# Séance 12 - Projet noté : Calcul Parallèle

# Présentation du projet
Une page web présentant le projet et permettant de télécharger les fichiers liés est disponible sur [ce site web](https://ray-tracing-rendu.vercel.app/)
## Préambule

L'objectif de ce projet est d'illustrer un aspect de la programmation répartie : le calcul parallèle. En particulier, nous nous concentrerons sur la parallélisation des données. Cette approche est pertinente lorsque nous avons besoin de réaliser un traitement qui dépasse les capacités d'un ordinateur personnel. Ayant accès à un ensemble de machines, nous pouvons diviser le gros calcul en petits morceaux et les transmettre à ces machines pour obtenir le résultat final.

## Questions

Pour réaliser cela, nous devons imaginer une stratégie de partitionnement et d'assignation des tâches de calcul à différentes machines. Une fois les résultats obtenus, nous devons concevoir un moyen de les recombiner pour obtenir le résultat final.

## Le tracé de rayon (raytracing)

Le raytracing est un type de calcul très gourmand en cycles CPU. Il s'agit d'un algorithme de synthèse d'image qui calcule la scène pixel par pixel pour finalement produire une image. La scène est décrite sous la forme d'un ensemble de formes géométriques et de sources lumineuses.

### Question

Il est essentiel de tester le programme en modifiant ses paramètres (sur la ligne de commande) et observer le temps d'exécution en fonction de la taille de l'image calculée. Une courbe montrant le temps de calcul par rapport à la taille de l'image peut être utile pour comprendre l'efficacité du programme.

## Accélérons les choses

Pour calculer une image de haute résolution, il est préférable de paralléliser les calculs sur un ensemble de machines. Notre scénario comprend :

1. Un ensemble de nœuds de calcul, capables de calculer un morceau d'une scène.
2. Un serveur de nœuds qui nous permet de récupérer les coordonnées des nœuds de calcul.
3. Un programme qui découpe le calcul, récupère les coordonnées des nœuds disponibles, envoie un calcul sur chacun et affiche le résultat.

### Questions

Il est important de réaliser un schéma de cette architecture en identifiant les processus fixes et éphémères, les types de données échangées entre les processus et la stratégie pour effectuer les calculs en parallèle. Enfin, il faut mettre en œuvre cette application répartie et vérifier que le calcul est bien accéléré.

---

Dernière modification: vendredi 9 juin 2023, 11:03

