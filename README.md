## Orodje za računalniško podprto prevajanje - Maturitetna seminarska naloga


## Dokumentacija:

- Word dokument:

  https://github.com/anzepintar/ozrpp/docs/Word/

- PowerPoint dokument:

  https://github.com/anzepintar/ozrpp/docs/PowerPoint/

- PDF dokument:

  https://github.com/anzepintar/ozrpp/docs/PDF/

## Struktura projekta
```text
pom.xml
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       ├── anzepintar/
│   │       │   └── ozrpp/
│   │       │       ├── Ozrpp.java
│   │       │       ├── OzrppLauncher.java
│   │       │       ├── controlers/
│   │       │       │   ├── EditorController.java
│   │       │       │   ├── LauncherController.java
│   │       │       │   └── ProjectPropertiesController.java
│   │       │       ├── customcotrols/
│   │       │       │   ├── AutoResizableTextArea.java
│   │       │       │   └── TranslationCheckBox.java
│   │       │       ├── editordata/
│   │       │       │   └── TableRow.java
│   │       │       ├── fileexport/
│   │       │       │   ├── FileExporter.java
│   │       │       │   ├── TmxSaver.java
│   │       │       │   └── TmxToXliffSaver.java
│   │       │       ├── fileimport/
│   │       │       │   ├── FileImporter.java
│   │       │       │   └── TmxLoader.java
│   │       │       └── generatedclasses/
│   │       │           ├── tmxgenerated/
│   │       │           │   ├── Body.java
│   │       │           │   ├── Bpt.java
│   │       │           │   ├── Ept.java
│   │       │           │   ├── Header.java
│   │       │           │   ├── Hi.java
│   │       │           │   ├── It.java
│   │       │           │   ├── Map.java
│   │       │           │   ├── Note.java
│   │       │           │   ├── ObjectFactory.java
│   │       │           │   ├── Ph.java
│   │       │           │   ├── Prop.java
│   │       │           │   ├── Sub.java
│   │       │           │   ├── Tmx.java
│   │       │           │   ├── Tu.java
│   │       │           │   ├── Tuv.java
│   │       │           │   ├── Ude.java
│   │       │           │   └── Ut.java
│   │       │           └── projectproperties/
│   │       │               ├── ProjectProperites.java
│   │       │               └── ProjectPropertiesManager.java
│   │       └── module-info.java    
│   └── resources/
│       ├── langAndCodes.csv
│       ├── img/
│       │   ├── icon.ico
│       │   ├── icon.png
│       │   └── icon.svg
│       ├── schemas/
│       │   ├── schemasHelp.md
│       │   ├── tmx/
│       │   │   └── tmx14.dtd
│       └── ui/
│           ├── editorScene.fxml
│           ├── launcherScene.fxml
│           └── projectPropertiesScene.fxml
└── test/
    ├── java/
    │   ├── fileexport/
    │   │   └── FileExporterTest.java
    │   ├── fileimport/
    │   │   ├── FileImporterTest.java
    │   │   └── TmxLoaderTest.java
    │   └── projectproperties/
    │       └── ProjectPropertiesTest.java  
    └── resources/
        ├── fileexport/
        │   ├── sloveniatext.docx.tmx
        │   ├── sloveniatext.odt.tmx
        │   └── sloveniatext.txt.tmx
        └── fileimport/
            ├── sloveniatext.docx
            ├── sloveniatext.odt
            ├── sloveniatext.tmx
            └── sloveniatext.txt
```