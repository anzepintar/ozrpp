# Schemas Help

## Sources:
- tmx14:
  - https://www.ttt.org/oscarStandards/
  - https://www.ttt.org/oscarStandards/tmx/tmx14.dtd
- xliff
  - http://docs.oasis-open.org/xliff/xliff-core/v2.1/os/xliff-core-v2.1-os.html
  - https://docs.oasis-open.org/xliff/xliff-core/v2.1/os/xliff-core-v2.1-os.zip

## Converting:
- .xsd to java classes
```
xjc .\resources\schemas\xliff\xliff-core-v2.1-os\schemas\xliff_core_2.0.xsd -d .\java\com\anzepintar\ozrpp\converters\xliffconvert\ 
```

- .dtd to java classes
```
xjc -d .\java\com\anzepintar\ozrpp\converters\ -dtd .\resources\schemas\tmx\tmx14.dtd ```
```
**potrebno je zamenjati javax v jakarta**