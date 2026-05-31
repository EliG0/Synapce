```text
╭─╮╭─╴╭─╮╭─╮╭─╮╷╶┬╴╭─╮╭─╮╷╭─╴╭─╮
├┬╯├╴ ├─╯│ │╰─╮│ │ │ │├┬╯│├╴ ╰─╮
╵╰╴╰─╴╵  ╰─╯╰─╯╵ ╵ ╰─╯╵╰╴╵╰─╴╰─╯
```

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -         

# Репозитории

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -     

### GameRepository

Отвечает за игры, которые поддерживает Хермеус (например, Skyrim, Fallout, Morrowind).

* `List<Game> findAll()` — получить список всех поддерживаемых менеджером игр.
* `Optional<Game> findById(int gameId)` — найти игру по её уникальному ID.
* `void save(Game game)` — добавить новую игру в список поддерживаемых или обновить настройки существующей (например,
  изменить путь к папке с игрой).
* `boolean deleteById(int gameId)` — удалить игру из списка поддерживаемых менеджером.

> см. [GameRepository.java](../../src/main/java/ru/lgtu/xarxes/hermaeus/repository/GameRepository.java)
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

### ModRepository

Отвечает за реестр модов. Глобальная БД.

* `List<ModMetaData> findAll()` — получить полный список метаданных всех существующих модов
* `List<ModMetaData> findByGameId(int gameId)` — отфильтровать и выдать список модов, которые подходят только для
  конкретной игры.
* `Optional<ModMetaData> findById(String modId)` — найти мод по его уникальному строковому ID.
* `void save(ModMetaData mod)` — добавить новый мод в глобальный реестр или обновить информацию о существующем

> см. [ModRepository.java](../../src/main/java/ru/lgtu/xarxes/hermaeus/repository/ModRepository.java)
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

### FileRepository

* `List<T> loadlist(String filePath, Class<T[]> arrayClass)` -
* `void savelist(String filePath, List<T> data)` -

> см. [FileRepository.java](../../src/main/java/ru/lgtu/xarxes/common/repository/FileRepository.java)
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

### UserRepository

Отвечает за хранене данных обо всеми зарегистрированными пользователям

* `List<User> findAll()` — получить всех пользователей из базы.

* `Optional<User> findById(int id)` — найти пользователя по первому ключу (ID).

* `Optional<User> findByUsername(String username)` — найти пользователя по уникальному логину.

* `void save(User user)` — сохранить нового пользователя или обновить существующего.

* `void saveAll(List<User> users)` — пакетное сохранение/обновление списка пользователей.

* `boolean deleteById(int id)` — удалить запись из базы по ID (возвращает true, если строка была удалена).

> см. [UserRepository.java](../../src/main/java/ru/lgtu/xarxes/common/repository/UserRepository.java)
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

### InstalledModRepository

* `List<InstalledMod> findAll(int userId)` — получить быстрый список всех установленных модов конкретного пользователя
* `Optional<InstalledMod> findById(int userId, int gameId, String modId)` — найти и прочитать детальный meta.toml
  конкретного мода для выбранной игры.
* `void save(int userId, int gameId, InstalledMod mod)` — сохранить/обновить данные мода
* `void saveAll(int userId, List<InstalledMod> mods)` — пакетное обновление списка модов
* `boolean deleteById(int userId, int gameId, String modId)` — удалить мод у пользователя

> см. [InstalledModRepository.java](../../src/main/java/ru/lgtu/xarxes/oghma/repository/InstalledModRepository.java)

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
