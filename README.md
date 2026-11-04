# petri_network — Simulateur de réseaux de Petri

Implémentation Java d'un réseau de Petri (places, transitions, arcs classiques, arcs zéro et arcs videurs), réalisée pour le Fil Rouge FR-PN de l'UE DCL-MAPD (IMT Atlantique, 2026-2027).

Binôme : Alexis Pujol, Benoît Lot.

## Contenu du dépôt

```text
petri_network/
├── pom.xml                         # Build Maven et dépendances JUnit 5
└── src/
    ├── main/                       # Code du réseau de Petri
    │   ├── PetriLatticeInterface.java  # Interface de service : construire et modifier un réseau
    │   ├── PetriLattice.java       # Le réseau : listes des places, transitions et arcs
    │   ├── Place.java              # Place et son nombre de jetons
    │   ├── Transition.java         # Arcs entrants/sortants, isStepable(), step()
    │   ├── Arc.java                # Arc abstrait : nom, poids, place reliée
    │   ├── InArc.java              # Arc place -> transition (consomme des jetons)
    │   ├── OutArc.java             # Arc transition -> place (produit des jetons)
    │   ├── ArcZero.java            # Arc zéro : actif seulement si la place source est vide
    │   └── ArcEmpty.java           # Arc videur : actif dès qu'il y a un jeton, vide la place au tir
    └── test/
        ├── TestPlace.java          # Tests JUnit de Place
        ├── TestTransition.java     # Tests JUnit de Transition et du tir
        ├── TestInArc.java          # Tests JUnit de InArc, ArcEmpty et ArcZero
        ├── TestOutArc.java         # Tests JUnit de OutArc
        ├── AllTests.java           # Suite JUnit qui lance TestPlace et TestTransition
        └── TestPetriLattice.java   # Scénario de démonstration (main, sans JUnit)
```

## Prérequis

- JDK 21 ou plus récent.
- VS Code avec l'extension **Extension Pack for Java**, ou IntelliJ IDEA.

## Lancer la démonstration

`TestPetriLattice` construit un réseau à une transition (2 arcs entrants, 2 arcs sortants, un arc zéro, un arc videur), affiche les places avant et après un tir, puis supprime une place et ses arcs.

- **VS Code / IntelliJ** : ouvrir `src/test/TestPetriLattice.java` puis cliquer sur *Run* au-dessus de `main`.
- **Ligne de commande**, depuis `petri_network/` :

```bash
javac -d out src/main/*.java src/test/TestPetriLattice.java
java -cp out TestPetriLattice
```

## Lancer les tests

- **VS Code** : ouvrir le dossier `petri_network`, attendre « Java: Ready », puis panneau *Testing* (icône fiole) → lancer `AllTests`.
- **IntelliJ** : *File > Open* → `pom.xml`, puis triangle vert à côté de `AllTests`.

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

## Description des tests

61 tests JUnit 5, répartis en quatre classes. Ils sont adaptés du tutoriel JUnit fourni par l'enseignant. Chaque test repart d'un état neuf (`@BeforeEach`), donc l'ordre d'exécution n'a pas d'importance.

`AllTests` lance `TestPlace`, `TestTransition`, `TestInArc` et `TestOutArc`.

### `TestPlace` (10 tests)

Avant chaque test : une place `P0` vide.

| Test | Ce qui est vérifié |
|---|---|
| `testConstructeur` | `new Place("P3", 3)` a le bon nom et 3 jetons |
| `testGetToken` | `P0` contient 0 jeton |
| `testGetName` | `getName()` renvoie `P0` |
| `testSetName` | `setName("P1")` change le nom |
| `testToString` | L'affichage vaut `Place (P0) : 0` |
| `testAddToken` | Ajouts successifs : 0 + 2 = 2, puis 2 + 3 = 5 |
| `testRemoveToken` | Retraits successifs : 3 − 1 = 2, puis 2 − 2 = 0 |
| `testConstructeurJetonsNegatifs` | Créer une place avec −2 jetons lève `IllegalArgumentException` (exigence 7) |
| `testRemoveTokenPlusQueDisponible` | Retirer 2 jetons d'une place qui n'en a qu'un lève `IllegalArgumentException` (exigence 7) |
| `testAddTokenNegatif` | `addToken(-3)` lève `IllegalArgumentException` (exigence 7) |

### `TestTransition` (18 tests)

Avant chaque test, le réseau du tutoriel est reconstruit :

```text
P0 (1 jeton) --1--> T0 --2--> P1 (0 jeton) --1--> T1 --2--> P0
```

Les tests d'arcs zéro et videurs ajoutent leur propre place et leur propre transition à ce réseau.

| Test | Ce qui est vérifié |
|---|---|
| `testConstructeur` | Une transition neuve a le bon nom et aucun arc |
| `testSetName` | `setName()` change le nom |
| `testAddInArc` | `addInArc` crée un arc entrant de poids 2, relié à la bonne place, rangé dans la transition et dans le réseau |
| `testAddOutArc` | Même chose pour `addOutArc` avec un arc sortant |
| `testAddArcsDirectementSurTransition` | `Transition.addInArc` et `addOutArc` rangent des arcs créés à la main |
| `testUnSeulArcEntrantParPlace` | Un 2e arc P0 → T0 lève `IllegalArgumentException` (exigence 3) |
| `testUnSeulArcSortantParPlace` | Un 2e arc T0 → P1 lève `IllegalArgumentException` (exigence 3) |
| `testIsStepable` | T0 est tirable (P0 a le jeton demandé), T1 ne l'est pas (P1 est vide) |
| `testStep` | Tirer T0 donne P0 = 0 et P1 = 2 ; ensuite T0 n'est plus tirable et T1 le devient |
| `testStepNonTirableNeChangeRien` | Tirer T1, non tirable, ne modifie aucune place |
| `testDeuxStepsSuccessifs` | Un 2e tir de T0 est sans effet (P0 = 0, P1 = 2) |
| `testCycle` | Tirer T0 puis T1 donne P0 = 2 et P1 = 1 |
| `testTransitionSansArcEntrantEstTirable` | Une transition sans arc entrant, reliée à une place vide, est tirable, ajoute 1 jeton et reste tirable (cas proposé dans le cahier des charges) |
| `testArcZeroPlaceVideTirable` | Avec un arc zéro sur une place vide, la transition est tirable |
| `testArcZeroStepNeModifiePasLaPlace` | Le tir par un arc zéro ne retire rien de la place source, et l'arc sortant ajoute bien 1 jeton |
| `testArcZeroPlaceNonVideNonTirable` | Avec un arc zéro sur une place qui contient un jeton, la transition n'est pas tirable |
| `testArcVideurVideLaPlace` | Avec un arc videur sur une place à 3 jetons, la transition est tirable et le tir vide la place |
| `testArcVideurPlaceVideNonTirable` | Avec un arc videur sur une place vide, la transition n'est pas tirable |

### `TestInArc` (22 tests)

Avant chaque test : une place `P0` vide et un arc entrant `A0` de poids 3 relié à `P0`. Les tests d'`ArcEmpty` et d'`ArcZero` créent leur propre arc (`AE0`, `AZ0`) sur cette même place.

| Test | Ce qui est vérifié |
|---|---|
| `testConstructeurInArc` | `A0` a le bon nom, le poids 3 et la place `P0` |
| `testConstructeurInArcPoidsNegatif` | Créer un arc de poids −1 lève `IllegalArgumentException` |
| `testGetWeight` | `getWeight()` renvoie 3 |
| `testGetName` | `getName()` renvoie `A0` |
| `testSetName` | `setName("A1")` change le nom |
| `testGetPlace` | `getPlace()` renvoie la place `P0` |
| `testSetPlace` | `setPlace()` relie l'arc à une autre place (`P1`) |
| `testToString` | L'affichage vaut `Arc entrant (A0) : 3` |
| `testSetWeight` | `setWeight(5)` change le poids |
| `testSetWeightNegatif` | `setWeight(-1)` lève `IllegalArgumentException` |
| `testIsActive` | Rattaché à une transition, l'arc est inactif quand `P0` est vide et devient actif avec 3 jetons (poids) |
| `testStep` | Tirer l'arc sur une place vide lève `IllegalArgumentException` ; avec 5 jetons, le tir en retire 3 (il en reste 2) |
| `testConstructeurArcEmpty` | `AE0` a le bon nom, un poids de 0 et la place `P0` |
| `testIsActiveArcEmpty` | L'arc videur est inactif sur une place vide et actif dès qu'elle contient 1 jeton |
| `testStepArcEmpty` | Tirer l'arc videur sur une place vide lève `IllegalArgumentException` ; avec 5 jetons, le tir vide la place |
| `testSetWeightArcEmpty` | `setWeight(1)` sur un arc videur lève `UnsupportedOperationException` |
| `testToStringArcEmpty` | L'affichage vaut `Arc entrant vide (AE0)` |
| `testConstructeurArcZero` | `AZ0` a le bon nom, un poids de 0 et la place `P0` |
| `testIsActiveArcZero` | L'arc zéro est actif sur une place vide et inactif dès qu'elle contient 1 jeton |
| `testStepArcZero` | Tirer l'arc zéro sur une place vide ne change rien ; avec 5 jetons, le tir lève `IllegalArgumentException` |
| `testSetWeightArcZero` | `setWeight(1)` sur un arc zéro lève `UnsupportedOperationException` |
| `testToStringArcZero` | L'affichage vaut `Arc entrant zéro (AZ0)` |

### `TestOutArc` (11 tests)

Avant chaque test : une place `P0` vide et un arc sortant `A0` de poids 3 relié à `P0`.

| Test | Ce qui est vérifié |
|---|---|
| `testConstructeurOutArc` | `A0` a le bon nom, le poids 3 et la place `P0` |
| `testConstructeurOutArcPoidsNegatif` | Créer un arc de poids −1 lève `IllegalArgumentException` |
| `testGetWeight` | `getWeight()` renvoie 3 |
| `testGetName` | `getName()` renvoie `A0` |
| `testSetName` | `setName("A1")` change le nom |
| `testGetPlace` | `getPlace()` renvoie la place `P0` |
| `testSetPlace` | `setPlace()` relie l'arc à une autre place (`P1`) |
| `testToString` | L'affichage vaut `Arc sortant (A0) : 3` |
| `testSetWeight` | `setWeight(5)` change le poids |
| `testSetWeightNegatif` | `setWeight(-1)` lève `IllegalArgumentException` |
| `testStep` | Le tir ajoute 3 jetons à `P0` : 0 puis 3, puis 6 au 2e tir |

### `TestPetriLattice` (scénario manuel)

Ce n'est pas un test JUnit : c'est un `main` sans assertions, dont on lit le résultat dans la console (voir « Lancer la démonstration »). Il construit une transition `tr1` avec deux arcs entrants (`pl1`, poids 2 ; `pl2`, poids 3), deux arcs sortants (`pl3`, poids 4 ; `pl4`, poids 1), un arc zéro depuis `pl5` et un arc videur depuis `pl6`. Il affiche les places et `isStepable()` avant et après un `step()`, puis supprime `pl6` et affiche la liste des arcs pour contrôler que l'arc videur a disparu.

### Ce que les tests ne couvrent pas

- La suppression d'éléments : `removeArc`, `removePlace` et `removeTransition` (`removePlace` n'est exercée que par le scénario `TestPetriLattice`).
- Les doublons créés avec `addArcZero` ou `addArcEmpty` (l'exigence 3 n'est testée que pour `addInArc` et `addOutArc`).
- L'affichage du réseau (`PetriLattice.toString()`).
