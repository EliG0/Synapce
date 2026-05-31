```text
╭─╮╶┬╴╭─╮╷ ╷╭─╴╶┬╴╷ ╷╭─╮╭─╴
╰─╮ │ ├┬╯│ ││   │ │ │├┬╯├╴ 
╰─╯ ╵ ╵╰╴╰─╯╰─╴ ╵ ╰─╯╵╰╴╰─╴
```

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

## Xarxes

ядро, включает: common, config, Launcher(Main)
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

### common

содержит всё необходимое для общей работы с данными в частности:

* /core - [Operation Result](../../src/main/java/ru/lgtu/xarxes/common/core/OperationResult.java)
  и [LogService](../../src/main/java/ru/lgtu/xarxes/common/core/LogService.java)
* /model - модели данных (User, Role, Theme), см. [XarxesModel](../Models/Models.md)
* /repository - репозитории для работы с данными (UserRepository, FileRepository),
  см. [Repositories](../Repositories/Repositories.md)
* /service - сервисы для работы с данными, см. [Services](../Services/Services.md#xarxes)
* /utils - утилиты для работы со своими данными, см. [Utils](../Utils/Utils.md)

### config

конфигурация приложения и запуск, в частности:

* [AppContext](../../src/main/java/ru/lgtu/xarxes/config/AppContext.java) - че то там про единоразовую инициализацию
  всех сервисов, а после передачи кому нужно
* [BootService](../../src/main/java/ru/lgtu/xarxes/config/BootService.java) - Проверяет систему при запуске, пока только
  проверяет БД и в случае отсутствия создает её
* [ConfigService](../../src/main/java/ru/lgtu/xarxes/config/ConfigService.java) - Загружает настройки из
  config.properties
* [DebugService](../../src/main/java/ru/lgtu/xarxes/config/DebugService.java) - личная штука для тестов, создает
  тестовые данные в БД

### UI - Интерфейс самого приложения:

> TODO: Займусь этим когда буду делать интерфейс

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

```txt
xarxes/
├── common/
│   ├── core/
│   │   ├── LogService.java
│   │   └── OperationResult.java
│   ├── model/
│   ├── repository/
│   ├── service/
│   └── utils/
├── config/
│   ├── AppContext.java
│   ├── BootService.java
│   ├── ConfigService.java
│   └── DebugService.java
├── ui/
├── Launcher.java
└── Main.java
```

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

# Hermaeus

Каталог модов и интеграция с играми, включает: модели, репозитории, сервисы и свой UI.
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

### Model: см. [HermaeusModels.md](../Models/Models.md)

### Repository:

[GameRepository](../Repositories/Repositories.md#modrepository)

[ModRepository](../Repositories/Repositories.md#gamerepository)

### Service:

[GameService](../Services/Services.md#gameservice)

[ModService](../Services/Services.md#modservice)

[SearchService](../Services/Services.md#searchservice)

### UI:

> TODO: Займусь этим когда буду делать интерфейс

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

```text
hermaeus/
├── model/
│   ├── Game.java
│   ├── Mod.java
│   ├── ModCategory.java
│   ├── ModStatus.java
│   └── ModSummary.java
├── repository/
│   ├── GameRepository.java
│   └── ModRepository.java
├── service/
│   ├── GameService.java
│   ├── ModService.java
│   └── SearchService.java
└── ui/
```

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

# Oghma

Менеджер модов, включает: модель, репозиторий, сервис и свой UI.

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

### Model:

[Conflict](../Models/Models.md#conflict)

[InstalledMod](../Models/Models.md#installedmod)

### Repository:

[InstalledModRepository](../Repositories/Repositories.md#installedmodrepository)

### Service:

[InstalledModService](../Services/Services.md#installedmodservice)

### UI:

> TODO: Займусь этим когда буду делать интерфейс

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

```text
oghma/
├── model/
│   ├── Conflict.java
│   └── InstalledMod.java
├── repository/
│   └── InstalledModRepository.java
├── service/
│   └── InstalledModService.java
└── ui/
```
