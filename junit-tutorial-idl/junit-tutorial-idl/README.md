# Tutoriel JUnit 5 — Réseau de Petri (IntelliJ IDEA)

## 1. Prérequis

- Installer **IntelliJ IDEA** (Community convient).
- Installer un **JDK 21** (Java Development Kit).
- Maven est intégré à IntelliJ pour l'import du projet. Pour utiliser `mvn` dans un terminal externe, installer Maven et vérifier qu'il est dans le `PATH`.

Vérifier les installations dans un terminal :

```bash
java -version
mvn -version
```

Si `mvn` est introuvable, utiliser le panneau **Maven** d'IntelliJ ou installer Maven.

## 2. Organisation du projet

```text
junit-tutorial-idl/
├── pom.xml                    # Configuration Maven et JUnit
├── README.md                  # Ce guide
└── src/
    ├── main/
    │   └── java/              # Code du réseau de Petri
    │       ├── Arc.java
    │       ├── Place.java
    │       ├── Transition.java
    │       ├── PetriNet.java
    │       └── PetriNetInterface.java
    └── test/
        └── java/              # Tests JUnit 5
            ├── TestPlace.java
            ├── TestTransition.java
            └── AllTests.java
```

## 3. Ouvrir le projet dans IntelliJ IDEA

1. Décompresser l'archive ZIP.
2. Dans IntelliJ, choisir **File > Open**.
3. Sélectionner le dossier `junit-tutorial-idl` contenant `pom.xml`.
4. Accepter l'ouverture du projet et attendre le chargement des dépendances Maven.
5. Si nécessaire, ouvrir le panneau **Maven** et cliquer sur **Reload All Maven Projects**.

## 4. Configurer Java et les dossiers sources

1. Aller dans **File > Project Structure > Project**.
2. Sélectionner **SDK : JDK 21** et un niveau de langage compatible avec Java 21.
3. Dans la vue **Project**, vérifier que `src/main/java` est marqué **Sources Root** (bleu) et que `src/test/java` est marqué **Test Sources Root** (vert).
4. Si un dossier n'est pas reconnu, clic droit dessus > **Mark Directory as** > **Sources Root** ou **Test Sources Root**.
5. Vérifier que les imports `org.junit.jupiter.api.*` ne sont pas en rouge ; sinon, recharger Maven.

## 5. Lancer les tests

### Dans IntelliJ

- Ouvrir `src/test/java/TestPlace.java` et cliquer sur le triangle vert à côté de la classe ou d'une méthode de test.
- Faire de même pour `TestTransition.java`.
- Pour lancer les deux classes ensemble, ouvrir `AllTests.java` et cliquer sur le triangle vert à côté de la classe.

### Avec Maven

Depuis le dossier qui contient `pom.xml` :

```bash
mvn test
```

Pour nettoyer puis relancer les tests :

```bash
mvn clean test
```

Pour lancer une seule classe :

```bash
mvn -Dtest=TestPlace test
```

**Remarque :** Maven Surefire découvre automatiquement `TestPlace` et `TestTransition` (leurs noms commencent par `Test`). `AllTests` sert principalement à les regrouper pour une exécution depuis IntelliJ ; son nom n'est pas sélectionné automatiquement par la convention par défaut de Surefire.

## 6. Comprendre les résultats

- **Vert** : le test a réussi.
- **Rouge** : une assertion a échoué ou une exception a été levée.
- **`Cannot resolve symbol`** : vérifier le JDK, les imports et le rechargement Maven.
- **`No tests found`** : vérifier les annotations `@Test`, les dossiers de tests et la configuration JUnit.
- **`expected ... but was ...`** : comparer la valeur attendue avec le résultat obtenu.

Un test vert n'implique pas nécessairement que toutes les fonctionnalités sont correctes : les assertions doivent vérifier le comportement voulu.
