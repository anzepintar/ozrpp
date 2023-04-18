# Schemas Help

## Sources:
- tmx14:
  - https://www.ttt.org/oscarStandards/
  - https://www.ttt.org/oscarStandards/tmx/tmx14.dtd

## Converting:

- .dtd to java classes
```
xjc -dtd .\resources\schemas\tmx\tmx14.dtd -d .\java\com\anzepintar\ozrpp\generatedclasses\
```
**potrebno je zamenjati javax v jakarta**