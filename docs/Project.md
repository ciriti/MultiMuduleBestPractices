[![Android CI](https://github.com/ciriti/MultiMuduleBestPractices/actions/workflows/android-ci.yml/badge.svg)](https://github.com/ciriti/MultiMuduleBestPractices/actions/workflows/android-ci.yml)

# MultiModuleBestPractices

## Einführung

`MultiModuleBestPractices` ist eine Android-Anwendung, die mit einer modularen Architektur entwickelt wurde, um Skalierbarkeit, Testbarkeit und Wartbarkeit zu fördern. Sie zeigt bewährte Praktiken für die Strukturierung von Multi-Modul-Android-Apps unter Verwendung moderner Android-Entwicklungstools wie Jetpack Compose, Kotlin Coroutines und Dependency Injection (DI).

---

## Inhaltsverzeichnis

- [Einführung](#einführung)
- [Funktionen](#funktionen)
- [App-Ablauf](#app-ablauf)
- [Architektur](#architektur)
- [Modulübersicht](#modulübersicht)
- [Setup und Installation](#setup-und-installation)
- [Ausführen der Anwendung](#ausführen-der-anwendung)
- [Tests](#tests)
- [Abhängigkeiten](#abhängigkeiten)
- [Fehlerbehandlung](#fehlerbehandlung)

---

## Funktionen

- **Multi-Modul-Architektur**: Eine modulare Struktur für verbesserte Wiederverwendbarkeit und Teamzusammenarbeit.
- **Authentifizierung**: Sicherer Login und Sitzungsverwaltung.
- **Navigation**: Zentralisierte Navigation mit flexiblem NavGraph.
- **Home- und Profilbildschirme**: Funktionsspezifische Module für Kernfunktionen.
- **Zustandsverwaltung**: Klare Trennung zwischen UI-Zustand und Geschäftslogik.
- **Lokale und entfernte Datenverwaltung**: Verwendung von Room Database und Retrofit zur Datenquellenverwaltung.

---

## App-Ablauf

- Die App beginnt mit dem **Authentifizierungsbildschirm** für den Benutzerlogin.
- Nach der Authentifizierung können Benutzer auf den **Home-Bildschirm** und den **Profilbildschirm** zugreifen, auf denen funktionsspezifische Daten angezeigt werden.
- Die Navigation wird zentral über einen `NavGraph` verwaltet, der reibungslose Übergänge zwischen Modulen sicherstellt.

---

## Architektur

Die Anwendung folgt den Prinzipien der **Clean Architecture** und ist in Feature-Module organisiert. Jedes Modul ist weiter in **Daten-, Domain- und Präsentationsebenen** unterteilt, um eine bessere Trennung der Verantwortlichkeiten zu gewährleisten.

---

### Ebenen

1. **Präsentationsebene**:
    - UI-Bildschirme und -Komponenten.
    - ViewModels zur Zustandsverwaltung und Geschäftslogik.

2. **Domain-Ebene**:
    - Definiert die Kernlogik der Anwendung.
    - Beinhaltet Anwendungsfälle, Zustandsmodelle und Datenumwandlungslogik.

3. **Datenebene**:
    - Verantwortlich für die Datenverwaltung.
    - Lokale (Room-Datenbank) und entfernte (API-Dienste) Quellen.
    - Repository-Muster zur Datenabstraktion.

---

### Modulübersicht

#### **App-Modul**

- Fungiert als Einstiegspunkt der Anwendung.
- Enthält die Hauptaktivität, DI-Konfigurationen auf Anwendungsebene (`AppModule`) und Navigationseinstellungen (`SetupNavGraph`).

#### **Core-Modul**

- Gemeinsame Logik, die in allen Feature-Modulen verwendet wird.
- **Core/Data**:
    - `local`: Room-Datenbank-Entities, DAOs und Datenbankkonfiguration.
    - `remote`: Retrofit-Dienste und Netzwerkkonfigurationen.
    - `repository`: Gemeinsame Repositories (z. B. `AuthRepository`).
- **Core/UI**:
    - Gemeinsame UI-Komponenten (z. B. Navigationsleiste, Ladeindikatoren).

#### **Feature-Module**

- **Auth-Modul**:
    - Verantwortlich für Authentifizierung (Login, Logout, Sitzungsverwaltung).
    - Enthält eigenes `AuthRepository`, ViewModels und UI-Bildschirme (z. B. `AuthenticationScreen`).

- **Posts-Modul**:
    - Zeigt Benutzerbeiträge an.
    - Enthält ViewModels (`HomeViewModel`), Repositories und Datenmodelle für Beiträge.

- **Profile-Modul**:
    - Zeigt Benutzerprofildetails an.
    - Enthält ViewModels (`ProfileViewModel`), Repositories und Datenmodelle für Benutzerinformationen.

---

## Setup und Installation

### Voraussetzungen

- **Android Studio**: Neueste Version installiert.
- **Kotlin**: Stellen Sie sicher, dass Kotlin im IDE konfiguriert ist.

---

### Schritte zum Ausführen

1. Klonen Sie das Repository:

   ```bash
   git clone https://github.com/your-username/multimodulebestpractices.git
   cd multimodulebestpractices
   ```

2. Öffnen Sie das Projekt in Android Studio.

3. Synchronisieren Sie die Projektabhängigkeiten:

   ```bash
   ./gradlew dependencies
   ```

4. Führen Sie die App aus:

   ```bash
   ./gradlew assembleDebug
   ```

---

## Tests

- **Unit-Tests**:
  Führen Sie alle Unit-Tests aus:

  ```bash
  ./gradlew test
  ```

- **Instrumentation-Tests**:
  Führen Sie alle Android-Instrumentation-Tests aus:

  ```bash
  ./gradlew connectedAndroidTest
  ```

---

## Abhängigkeiten

- **Jetpack Compose**: Für den Aufbau der Benutzeroberfläche.
- **Koin**: Dependency Injection.
- **Retrofit**: REST-API-Integration.
- **Room**: Lokale Datenbank.
- **Kotlin Coroutines**: Asynchrone Programmierung.

---

## Fehlerbehandlung

Die App verwendet strukturierte Fehlerbehandlung mit ViewModels und Zustandsverwaltung, um ein konsistentes Verhalten und eine ordnungsgemäße Fehleranzeige im UI sicherzustellen.

### Either-Muster mit Arrow-Bibliothek

Das **Either-Muster** aus der **Arrow-Bibliothek** wird verwendet, um Operationen zu kapseln, die entweder zu einem Erfolg (`Right`) oder einem Fehler (`Left`) führen. Dieser Ansatz fördert einen funktionalen Programmierstil zur Fehlerbehandlung und fördert die Unveränderlichkeit sowie Typsicherheit.

Beispielimplementierung:

```kotlin
override suspend fun getPosts(): Either<Throwable, List<Post>> =
    check {
        val postsDto = postRepository.getPosts().getOrHandle { throw it }
        postsDto.map { postDto ->
            postDto.toDomain(postDto.userId)
        }
    }
```

### Vorteile des Either-Musters

- **Explizite Fehlerbehandlung**: Fehler sind Teil der Funktionssignatur, was sie explizit und leichter nachvollziehbar macht.
- **Unveränderliche Ergebnisse**: Das Ergebnis (`Either`) ist unveränderlich, was unvorhergesehene Änderungen des Fehlerzustands verhindert.
- **Strukturierte Fehler**: Die Verwendung benutzerdefinierter Fehlertypen wie `AppFailure.NetworkFailure` bietet eine detaillierte und kategorisierte Fehlerbehandlung.

### App-spezifische Fehlertypen

Die App definiert eigene Fehlertypen (`AppFailure`), um häufige Fehlerszenarien abzudecken:

1. **NetworkFailure**: Erfasst netzwerkbezogene Fehler (z. B. keine Internetverbindung).
2. **DatabaseFailure**: Behandelt Fehler bei Datenbankoperationen.
3. **ValidationFailure**: Behandelt ungültige Dateneingaben oder Argumente.
4. **UnknownFailure**: Erfasst alle nicht behandelten oder unerwarteten Fehler.

---

Dieser strukturierte Ansatz stellt sicher, dass Fehler umfassend verwaltet und durch die gesamte App propagiert werden, was zu einem besseren Benutzer-Feedback und einer zuverlässigeren Anwendung führt.

---
