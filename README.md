# Kotlin Perusteet

## Week 1

Tehtävässä tehtiin pieni kotlin Appi, jossa tehdään lista jota pystytään järjestellä nappien avulla päivämäärän perusteella, Piilottaa tekemätömät taskit, Vaihtaa taskin tehdyksi tai tekemättömäksi ja lisätä uusia taskejä.

Kielenä käytetty Kotlinia
Mock Dataa on 7 kappaletta Tiedostossa mockData
Listan parametrit asetettu tiedostssa Task
Funktiot napeille tehty tiedostossa KotlinFunktions

Composable Homescreenissä laitetaan napit ja käytetään Row, Column ja Modifier tekijöitä

Composable Homescreeniä kutsutaan Composable Greetingissä joka on muuten tyhjä ja Greetingiä kutsutaan Mainissä. Text fieldiä varten on oma Composable funktio

## Week 2

Tällä viikolla lisättiin tehtävään viewModel ja muutamia uusia toiminnallisuuksia kuten LazyColumn, johon on lisätty poisto iconit ja täpät tehdyksi ja tekemättömäksi merkkaamista varten.

Compose-tilanhallinta on hyvä, koska se on reaktiivinen framework, joten se päivittää listan heti kun listaan lisätään uusia tietoja. Compose toimii staten avulla.

remember toimii vain Composable funktion sisällä, kun taas viewModel toimii koko UI:n elin kaaren ajan. viewModel toimii paljon parempien listojen kanssa kuin remember, joka pää osin toimii vain tekstikenttien kanssa. viewModel on kestävämpi UI-tila kun taas remember on lyhyt aikaisempi UI-tila ettei se pysty olla päällä kovin kauan. viewModel on myös suojattu recompositiolta.

## Week 3

Tällä viikolla tehtiin MVVM-mallia käyttäen

MVVM-malli jakautuu kolmeen osaan Modeliin, Viewiin ja ViewModeliin. Näiden avulla pystytään selvästi erottaa logiikka ja UI toisistaan siten että ne eivät ole sekaisin yhdessä tai kahdessa tiedostossa. MVVM-mallia noudattaa ohjelmaa on Helppo testata, se on Reaktiivinen eli UI-päivittyy automaattisesti ja MVVM-koodia on helpompi laajentaa kuin joitakin toisia koodeja.

StateFlow on Kotlin ruutini, joka mahdollistaa koodin reaktiivisuuden, koska Stateflown avulla koodi päivittää heti tilan kun se muuttuu ja se muuttuu heti aktiiviseksi kun StateFlowta kuunnellaan.

MVVM-mallin, Stateflown ja Jetpack Composen avulla saadan tehokas ja moderni Android sovellus MVVM monien etujen ansiosta.
