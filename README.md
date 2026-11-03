# petri_network — Simulateur de réseaux de Petri

Implémentation Java d'un réseau de Petri (places, transitions, arcs classiques, arcs zéro et arcs videurs), réalisée pour le Fil Rouge FR-PN de l'UE DCL-MAPD (IMT Atlantique, 2026-2027).

Binôme : Alexis Pujol, Benoît Lot.

## Contenu du dépôt

```text
petri_network/
├── pom.xml                         # Build Maven et dépendances JUnit 5
├── src/
│   ├── main/                       # Code du réseau de Petri
│   │   ├── PetriLatticeInterface.java  # Interface de service : construire et modifier un réseau
│   │   ├── PetriLattice.java       # Le réseau : listes des places, transitions et arcs
│   │   ├── Place.java              # Place et son nombre de jetons
│   │   ├── Transition.java         # Arcs entrants/sortants, isStepable(), step()
│   │   ├── Arc.java                # Arc abstrait : nom, poids, place reliée
│   │   ├── InArc.java              # Arc place -> transition (consomme des jetons)
│   │   ├── OutArc.java             # Arc transition -> place (produit des jetons)
│   │   ├── ArcZero.java            # Arc zéro : actif seulement si la place source est vide
│   │   └── ArcEmpty.java           # Arc videur : actif dès qu'il y a un jeton, vide la place au tir
│   └── test/
│       ├── TestPlace.java          # Tests JUnit de Place
│       ├── TestTransition.java     # Tests JUnit de Transition, des arcs et du tir
│       ├── AllTests.java           # Suite JUnit qui lance TestPlace et TestTransition
│       └── TestPetriLattice.java   # Scénario de démonstration (main, sans JUnit)
└── junit-tutorial-idl/             # Tutoriel JUnit fourni par l'enseignant (référence, hors projet)
```

## Prérequis

- JDK 21 ou plus récent (le cours utilise Java 25).
- VS Code avec l'extension **Extension Pack for Java**, ou IntelliJ IDEA.
- Maven en ligne de commande : optionnel (les deux IDE l'embarquent).

## Lancer la démonstration

`TestPetriLattice` construit un réseau à une transition (2 arcs entrants, 2 arcs sortants, un arc zéro, un arc videur), affiche les places avant et après un tir, puis supprime une place et ses arcs.

- **VS Code / IntelliJ** : ouvrir `src/test/TestPetriLattice.java` puis cliquer sur *Run* au-dessus de `main`.
- **Ligne de commande**, depuis `petri_network/` :

```bash
javac -d out src/main/*.java src/test/TestPetriLattice.java
java -cp out TestPetriLattice
```

## Lancer les tests

- **VS Code** : ouvrir le dossier `petri_network`, attendre « Java: Ready », puis panneau *Testing* (icône fiole) → lancer `AllTests` du projet `petri_network` (les tests du tutoriel apparaissent aussi, sous `junit-tutorial-idl`).
- **IntelliJ** : *File > Open* → `pom.xml`, puis triangle vert à côté de `AllTests`.
- **Maven** : `mvn test`

## Utiliser le réseau

```java
PetriLattice reseau = new PetriLattice();
Place p1 = reseau.addPlace("P1", 2);
Place p2 = reseau.addPlace("P2", 0);
Transition t = reseau.addTransition("T");
reseau.addInArc("a1", 1, p1, t);   // P1 -1-> T
reseau.addOutArc("a2", 3, t, p2);  // T -3-> P2

if (t.isStepable()) {
    t.step();                      // P1 = 1, P2 = 3
}
System.out.print(reseau);
```

Les autres types d'arcs entrants s'ajoutent avec `addArcZero(nom, place, transition)` et `addArcEmpty(nom, place, transition)`. `step()` ne fait rien si la transition n'est pas tirable.

## Lien avec la conception (TD1 à TD3)

### Ce qui est conforme au modèle

- `PetriLattice` réalise `PetriLatticeInterface`, qui regroupe les opérations d'ajout et de suppression.
- Une `Transition` porte ses arcs entrants (`inArcs`) et sortants (`outArcs`). Le tir suit le diagramme de séquence : `isStepable()` vérifie chaque arc entrant (activité, puis jetons), puis `step()` tire les arcs entrants actifs et les arcs sortants.
- Un arc connaît sa place (navigabilité arc → place). Hiérarchie : `Arc` ← `InArc` / `OutArc`, et `InArc` ← `ArcZero` / `ArcEmpty`.
- Les jetons sont un attribut entier de `Place` (la variante avec une classe Jeton a été écartée au TD1).

### Modifications par rapport au modèle

| Modèle (livrable TD3) | Code | Raison |
|---|---|---|
| `Place`, `Transition`, `Arc` sont des interfaces imbriquées dans `PetriLatticeInterface` | Classes concrètes, sans interfaces imbriquées | Une seule implémentation par concept |
| Pas de nom sur les éléments | Attribut `name` sur `Place`, `Transition` et `Arc` ; constructeurs `Place(name, token)` et `Transition(name)` | Identifier les éléments à l'affichage |
| `addInArc(weight, place)`, `addOutArc(weight, place)`, `removeArc(arc)` | `addInArc(name, weight, place, transition)`, `addOutArc(name, weight, transition, place)`, `removeArc(arc, transition)` | L'arc doit être rattaché à sa transition |
| `addArcZero(weight, place)`, `addArcEmpty(weight, place)` | `addArcZero(name, place, transition)`, `addArcEmpty(name, place, transition)`, poids fixé à 0 | Le poids n'intervient pas pour ces arcs |
| `PetriLattice` ne stocke que `transitions` | Stocke aussi `places` et `arcs` | Nécessaire pour `removePlace` et l'affichage ; les arcs sont donc stockés à deux endroits (réseau et transition) |
| `InArc` a un attribut `active: boolean` | `isActive()` est calculé à partir de la place | L'activité dépend de l'état courant de la place |
| La transition retire/ajoute les jetons sur les places | Chaque arc a sa méthode `step()` | Polymorphisme : `ArcEmpty` redéfinit `step()` pour vider la place |
| `Arc` classe concrète | `Arc` classe abstraite | On instancie toujours un `InArc` ou un `OutArc` |

## Conformité au cahier des charges

| Exigence | État | Remarque |
|---|---|---|
| 1. Places, transitions et arcs | ✅ | `PetriLattice` |
| 2. Arcs place → transition et transition → place | ✅ | `InArc`, `OutArc` |
| 3. Un seul arc dans le même sens entre une place et une transition | ❌ | Non vérifié : un 2e arc est ajouté |
| 4. Poids : jetons requis et retirés / jetons ajoutés | ✅ | `isStepable()`, `InArc.step()`, `OutArc.step()` |
| 5. L'utilisateur choisit la transition à tirer | ✅ | `Transition.step()` |
| 6. Valeur d'un arc strictement positive | ❌ | Non vérifié dans le constructeur d'`Arc` ni dans `setWeight` |
| 7. Une place est vide ou contient des jetons | ✅ | `IllegalArgumentException` si une opération rendrait le nombre de jetons négatif |
| 8. Ajouter / supprimer places, transitions, arcs | ⚠️ | `removeTransition` laisse les arcs de la transition dans la liste `arcs` du réseau |
| 9. Ajouter / retirer des jetons | ✅ | `Place.addToken`, `Place.removeToken` |
| 10. Changer la valeur d'un arc | ✅ | `Arc.setWeight` (sans contrôle, cf. exigence 6) |
| Arc zéro (actif seulement si la place est vide) | ⚠️ | Place vide → tirable ✅ ; place non vide → la transition reste tirable ❌ (un arc inactif est ignoré par `isStepable`) |
| Arc videur (actif dès qu'il y a un jeton, vide la place) | ⚠️ | Vide bien la place ✅ ; place vide → la transition est tirable (interprétation à trancher) |

État des tests au 03/11/2026 : 24 tests JUnit sur 28 passent. Les 4 échecs correspondent aux lignes ❌ / ⚠️ des exigences 3 et des arcs zéro et videur.
