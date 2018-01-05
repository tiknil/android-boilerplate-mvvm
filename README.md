# Android Boilerplate

Il boilerplate contiene un app base Android che implementa l'architettura MVVM usando Dagger2, RxJava e Android Databinding.

## Design pattern

### Il pattern Model-View-ViewModel

I principali attori del pattern MVVM sono:
- La _View_ he informa il ViewModel sulle azioni dell'utente
- Il _ViewModel_  - espone flussi di dati rilevanti per la view
- Il _Model_  - astrae la fonte dei dati. Il ViewModel lavora con il DataModel per ottenere e salvare i dati.

A prima vista, il pattern MVVM sembra molto simile al modello Model-View-Presenter, perché entrambi svolgono un ottimo lavoro nell'astrazione dello stato e del comportamento della vista. Il Presentation Model astrae una View indipendente da una specifica piattaforma di interfaccia utente, mentre il pattern MVVM è stato creato per semplificare la programmazione **event driven** delle interfacce utente.

Se nel pattern MVP il Presenter indica direttamente alla View cosa visualizzare, nel MVVM **il ViewModel mostra i flussi di eventi** a cui le viste possono legarsi. In questo modo, il ViewModel non ha più bisogno di tenere un riferimento alla vista, come il Presenter fa. Ciò significa anche che tutte le interfacce che il pattern MVP richiede sono state eliminate.

Le View comunicano anche al ViewModel le diverse azioni. Pertanto, il pattern MVVM supporta l'associazione dati bidirezionale tra View e ViewModel e vi è una relazione many-to-one tra View e ViewModel. La View ha un riferimento al ViewModel ma **il ViewModel non ha informazioni sulla View**. Il consumatore dovrebbe conoscere il produttore, ma il produttore - il ViewModel - non sa, e non gli importa conoscere il consumatore.

### Model-View-ViewModel @ Tiknil

La parte event driven richiesta da MVVM viene eseguita utilizzando gli `Observable` di RxJava e gli `ObservableFields<>` di android-databinding:
- Un riferimento all'utilizzo gli `Observable` di RxJava con il pattern MVVM è possibile trovarlo a questo link: https://medium.com/upday-devs/android-architecture-patterns-part-3-model-view-viewmodel-e7eeee76b73b
- Un riferimento sulla configurazione e l'utilizzo del databinding di Android è possibile trovarlo a questo link: https://nullpointer.wtf/android/mvvm-architecture-data-binding-library/

### Inversion of Control e Dependency Injection
L'*Inversion of Control* (**IoC**) è un *software design pattern* secondo il quale ogni componente dell'applicazione deve ricevere il **controllo** da un componente appartenente ad una **libreria riusabile**.<br>
L'obiettivo è quello di rendere ogni componente il più indipendente possibile dagli altri in modo che ognuno sia modificabile singolarmente con conseguente maggior riusabilità e manutenibilità.

La *Dependency Injection* (**DI**) è una forma di *IoC* dove l'implementazione del pattern avviene stabilendo le dipendenze tra un componente e l'altro tramite delle *interfacce* (chiamate **interface contracts**).<br>
A tali interfacce viene associata un'implementazione in fase di istanziazione del componente (nel *costruttore*) oppure in un secondo momento tramite *setter*.<br>
In ogni caso è generalmente presente un oggetto **container** che si occupa di creare le istanze di ogni *interfaccia*; la configurazione di tale *container* può così influenzare le dipendenze tra i vari componenti.<br>
L'utilizzo della *DI* è molto utile per la realizzazione di test automatici, infatti modificando il *container* è possibile *mockare* le dipendenze che non si desidera testare.

References:
- [Semplice video che chiarisce il concetto di DI](https://www.youtube.com/watch?v=IKD2-MAkXyQ)

### Getting Started

Il boilerplate contiene il file ruby `boilerplatemvvm.rb` che permette di creare un nuovo progetto a partire dal boilerplate fornendo le principali informazioni per l'inizializzazione. Di seguito le istruzioni:

1. Entra nella cartella di questo repo
2. Rendi lo script `boilerplatemvvm.rb` eseguibile
   ```
    chmod +x boilerplatemvvm.rb
   ```    
3. Quindi eseguilo
   ```
   ./boilerplatemvvm.rb
   ```
4. Puoi dargli direttamente i parametri seguenti:
   a. `-n`/`--name` Nome del progetto (es: ProjectName)
   b. `-p`/`--package` Package name (es: com.tiknil.projectname)
5. Oppure:
   `-h`/`--help` Per visualizzare l'help

## Struttura del progetto

Definiamo in questo capitolo le best practices di Tiknil per l'impostazione del boilerplate **TiknilBoilerplateMVVM**.

### Struttura MVVM

Nel boilerplate corrente è presente l'implementazione di un esempio di implementazione che segue il pattern Model-View-ViewModel: la *splashscreen*. Nello specifico:
- `viewmodel.SplashScreenViewModel.java` che rappresenta il *ViewModel*, estende il `BaseViewModel` dal quale eredita il costruttore. Tutti i viewmodel devono estendere il `BaseViewModel`.
- `view.activities.SplashScreenActivity.java` che rappresente la *View*, essa contiene il riferimento al viewmodel injettato tramite l'annotation `@Inject`. Tutte le activity devono estendere la `AbstractBaseActivity`; in egual modo tutti i fragment dovranno estendere l'`AbstractBaseFragment`.
- `model.BaseModel` è la classe dedicata all'implementazioni della parte Model del pattern, in base ai modelli richiesti del contesto essa verrà implementata di conseguenza. Tutti i model devono estendere il `BaseModel`.

#### Databinding

L'utilizzo dell'android-databinding all'interno del viewmodel di un activity (o di un fragment) è vincolato alla specifica, all'interno del file `layout` relativo, del tag `layout` e `data` come definito di seguito:

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context=".view.activities.SplashScreenActivity">

    <data>
        <variable
            name="viewModel"
            type="com.tiknil.boilerplatemvvm.viewmodel.SplashScreenViewModel" />
    </data>

    <LinearLayout>
        ... contenuto dell'activity/fragment ...
    </LinearLayout>

</layout>
```
Dove `variable.name` è il nome assegnato al viewModel di tipo `variable.type`, in questo modo ciascuna proprietà bindabile di un elemento della view (es: `EditText`) può essere bindata come specificato di seguito:
```xml
<EditText
    android:id="@+id/name_edittext"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="@={viewModel.nameText}"
    android:visibility="@{viewModel.isHidden ? View.VISIBLE : View.GONE}"
    android:textSize="15dp" />
```

Per ulteriori dettagli in merito all'android-databinding rimandiamo alla documentazione ufficiale: [Data Binding Library](https://developer.android.com/topic/libraries/data-binding/index.html).

##### ObservableFields

Tra gli *observable data object* disponibili, quelli utilizzati nel nostro pattern sono gli`ObservableFields` e i suoi simili `ObservableBoolean`, `ObservableInt`,...  che permettono al binding di collegare un listener all'oggetto associato per ascoltare le modifiche delle proprietà su quell'oggetto.

A questo link si può trovare la documentazione: [Data Object](https://developer.android.com/topic/libraries/data-binding/index.html#data_objects)

###### Dichiarazione e utilizzo di un Observable

La dichiarazione degli Observable all'interno del viewmodel è la seguente:

```Java
// Dichiarazione di un ObservableFields
public ObservableField<String> nameText = new ObservableField<>("Mario Rossi");

// Dichiarazione di un ObservableBoolean
public ObservableBoolean isHidden = new ObservableBoolean(true);
```

###### Binding Dato => UI

Implementato tramite android-databing utilizzando gli oggetti di tipo `Observable`. Essi infatti hanno il metodo `set` che consente di modificare il contenuto di una proprietà e apportare il cambiamento sulla UI.
```Java
// Setting di un ObservableFields: testo dell'EditText settato a "Luigi Verdi"
nameText.set("Luigi Verdi");

// Setting di un ObservableBoolean: l'EditText viene nascosta
isHidden.set(false);
```

###### Binding UI => Dato

1. Android-databinding mette a disposizione per alcune classe di oggetti UI l'operatore `=`:
```xml
<EditText
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="@={viewModel.nameText}" />
```
L'aggiunta di `=` alla classe generata dal binding di chiamare il metodo setter di `nameText` ogni volta che il testo cambia. Ciò significa che l'`ObservableField` relativo al `name` contenuto nel viewmodel conterrà sempre il testo presente nell'`EditText`.

2. Nel caso in cui si richiedesse di eseguire un binding UI => Dato in l'operazione da eseguire da parte del viewmodel fosse più complessa, a seguito di un cambiamento o un evento proveniente della UI, si ricorre all'uso degli oggetti `Observable` di [`RxJava`](https://github.com/ReactiveX/RxJava) all'interno della view. Nell'esempio seguente e nell'implementazione di questo pattern, i listener sugli eventi UI sono stati implementati con [`RxBinding`](https://github.com/codepath/android_guides/wiki/RxJava-and-RxBinding):

```Java
// Esecuzione del metodo foo() del viewmodel quando viene lanciato l'evento clicks sul `button`
RxView.clicks(button)
    .throttleFirst(1000, TimeUnit.MILLISECONDS)   // observable attivo in modalità throttle
    .compose(bindToLifecycle())                   // observable attivo solamente durante il ciclo di vita della view
    .observeOn(AndroidSchedulers.mainThread())    // observable attivo sul mainThread
    .subscribe(l -> {
        getViewModel().foo();    // esecuzione del metodo del viewmodel
});
```

### Dependency Injection

L'injection viene realizzata tramite le librerie [Dagger2](https://github.com/google/dagger) e [dagger-android](https://google.github.io/dagger/android.html).  La documentazione è possibile trovarla a questo [link](https://google.github.io/dagger/). I componenti principali per la sua implementazione si trovano nel package `com.tiknil.boilerplatemvvm.di`:
- `AppComponent.java` classe definita tramite l'annotation `@Component` che tramite la specifica dei moduli utilizzati permette di eseguire l'injection. In questo tipo di implementazione viene definita una sola classe `Component` per l'intera applicazione.
- `AppModule.java` classe i cui metodi forniscono le *dependencies*. Seguendo le style guide, ciascun provider deve essere definito all'interno del package `com.tiknil.boilerplatemvvm.services` (es.: `ApiService.java` con la relativa interfaccia `IApiService.java`).
- `BuildersModule.java` classe astratta che, utilizzando l'`AndroidInjection` fornita dalla libreria `dagger-android` permette di injettare i viewmodel all'interno delle activity e nei fragment.
- `ViewModelModule.java` classe che implementa il submodule utilizzato in `BuildersModule` per la specifica dei viewmodel *"providers"*.

I providers, qui definiti all'interno del package `com.tiknil.boilerplatemvvm.services`, hanno un costruttore che, se necessario, viene definito con l'annotation `@Inject` in modo tale da esplicitare a dagger la necessità di injettare gli oggetti passati.

### Cartelle di progetto

La cartella contenente il codice sorgente dell'app avrà la seguente struttura:

```
|--java
  |--com.tiknil.boilerplatemvvm
    |-- di                # Classi per l'implementazione della dependency injection con Dragger2
    |-- model             # Tutti gli oggetti model
    |-- services          # Oggetti che forniscono servizi (providers) come networking, persistenza dei dati, ecc...
    |-- utils             # Classi di generico aiuto per tutto l'app
    |-- view              # Le classi che implementano la ui
        |-- activities    # Tutte le activity eventualmente inseriti in sottocartelle di sezione
        |-- fragments     # Tutte i fragment eventualmente inseriti in sottocartelle di sezione
    |-- viewmodel         # Tutti i viewmodel eventualmente inseriti in sottocartelle di sezione
|-- assets
    |-- fonts             # Contiene i file dei fonts
|-- res                   # Cartella di resources: color, drawable, layout,...
```

## Services

Chiamiamo **Service** una classe dedicata all'esecuzione di _business logic_ legata a una stesso ambito iniettabile come dipendenza ove necessario, tramite corrispettivo **ServiceProtocol**.

Esempi dei più utilizzati:

* **ApiService:** dedicato alle chiamate network alle API del server.
* **CacheService:** dedicato alla storicizzazione di dati (database, portachiavi, file).
* **DataService:** dedicato all'utilizzo di dati temporanei disponibili solo in fase di esecuzione.
* **LocationService:** dedicato alla gestione del geoposizionamento dell'utente.
* **BluetoothService:** dedicato alla gestione della comunicazione bluetooth.
* **WebSocketService:** dedicato alla comunicazione via WebSocket.
* _ecc..._
