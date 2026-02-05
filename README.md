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

## Week 4

Viikko 4 lisättiin navigaatiota Kotlin Appiin

#### Navigoingti Jetpack Composessa
Navigointi Jetpack Composessa on sitä, että eri näkymien välillä liikutaan Navigatio-compose kirjaston avulla ilman monia Activityjä. Tällä tavalla kaikki on yhden Activityn sisällä. 

#### NavHost ja NavController
NavCotroller vastaa navigoinnin ohjaamisesta. Sillä siirrytään näkymästä toiseen ja palataan takaisin
NavHost määrittelee sovelluksen navigaatiorakenteen. Se kertoo mitkä näkymät ovat osa navigaatiota ja määrittelee aloitusnäkymän.

#### Sovelluksen Navigaatio
HomeScreen on vakionapäänäkymä, josta pystytään vaihtaa toiseen päänäkymään CaledarScreen. 
Navigointi hoidetaan HomeScreenin TopBarissa ja CalendarScreenissä on lista-ikoni jolla hoidetaan navigaatio takaisin HomeScreeniin.

#### MVVM ja Navigointi
Sovelluksessa käytetään vain yhtä ViewModelia jota käytetään molemmissa HomeScreenissä ja CalendarScreenissä, joten kaikki ruudut toimivat samassa tilassa ja muutokset näkyvät heti molemmissa tiloissa.

#### ViewModel tilan jako
ViewModel sisältää tilan StateFlow-muodossa. HomeScreen ja CalendarScreen molemmat lukevat tilan CollectAsStaten avulla. Kun lisätään tai muokataan tehtävä, ViewModel päivittää tilan ja Compose päivittää heti näkymät

#### CalendarScreen
CalendarScreen on toteutettu dueDaten mukaan siten, että päivämäärät ovat Otsikkoina ja title ja description ovat jokaisen jälkeen omassa boxissaan. CalendarScreeniin on myös toteutettu nappi jolla voidaan järjestellä dueDaten mukaan vanhin tai uusin ensin.

#### AlertDialog addTask ja editTask
tehtävien lisäys ja muokkaus hoidetaan AlertDialog.kt:n avulla:
addTask toimiii HomeScreenistä Lisää tehtävä napista.
Dialogissa sitten pistetään title, description ja dueDate. Tallenna nappi Dialogissa kutsuu viewModelin Addtaskiä. Peruuta sulkee dialogin.

EditTask avataan painamalla olemassa olevaa tehtävää, Teksti kentät ovat samat kuin AddTaskissä. Tallenna nappi kutsuu viewModel.updateTask, Poista kutsuu viewModel.removeTask ja peruuta sulkee Dialogin ilman mitään muutoksia taskiin.
