# Schemas

## Sources:
- tmx15:
  - https://sourceforge.net/projects/tmx/files/
  - https://www.ttt.org/oscarStandards/tmx/tmx14b.html
- xliff
  - http://docs.oasis-open.org/xliff/xliff-core/v2.1/os/xliff-core-v2.1-os.html
  - http://docs.oasis-open.org/xliff/xliff-core/v2.1/os/schemas/xliff_core_2.0.xsd

## Converting:
- .dtd to .xsd 
```
xjc -p com.package -dtd /path/to/file.dtd
- ```
- .dtd to .java - potrebna je zamenjava knjižnice javax z jakarta, ter spremeniti lokacijo generiranih datotek
```
xjc -dtd .\src\main\resources\com\anzepintar\ozrpp\schemas\tmx\tmx15.dtd -p com.anzepintar.ozrpp
```
