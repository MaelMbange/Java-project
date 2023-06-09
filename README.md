# Java-project

Projet permettant de gerer les evenements d'un tournoi.

4 packages:

* Participants
* Interfaces
* Evenements
* Graphiques

---
## Participants

Package composé de l'ensemble des personnes participants du tournoi.

### Classes Composantes :
* [x] Classe Abstraite Members

  * ID (int)
  * Idcourrant (int)
  * DateEnregistrement (Calendar)
  * Nationalite (locale)
  * pseudo (string)

* [x] Classe Players extends Members
  * Role (Status) {Starter / Substitute} 
* [x] Classe Coach extends Members
* [x] Classe Equipe
  * 5 Joueurs (ArrayList<"Players">)
  * 1 Coach (Single object : Coach)
  * Nom d'equipe (String)
  * Description (String)
---
## Interface
Package implementant une seule interface :

* [x] getInstanceAt<T>
    * T GetinstanceAt(int n)


Cette interface permet de signer un contrat demandant de retourner un element (appartenant à une liste par exemple).

---
## Evenements

package composé de 2 classes permettant de gerer majoritairement le programme.

### Classe composantes
* [x] Classe Matches
  * EquipeA (Equipe)
  * EquipeB (Equipe)
  * ScoreA (int)
  * ScoreB (int)
  * RoundCourrant (int)
  * EtatDuMatch (boolean)
* [x] Classe Singleton Tournament
  * Liste de membres (Arraylist<"Members">)
  * liste de matches (ArrayList<"Matches">)
  * liste d' equipes (ArrayList<"Equipes">)
  * instance (static tournament)
  * NomDeTournoi (String)

---

## Diagramme UML

![UML](./UML.png)
