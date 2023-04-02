Zadanie pierwsze zostało wykonane z wykorzystaniem algorytmu dynamicznego, w celu ustalenia harmonogramu zadaniowego dla "pickerów".

Algorytm przyjmuje listę zamówień oraz informacje o sklepie (przechowywane w strukturze Store), a następnie ustala listę zadań, które mają być wykonywane o odpowiednich porach przez pracowników.

Algorytm iteruje po każdym pracowniku i dla każdego pracownika tworzy tabelę dynamiczną, w której każda komórka odpowiada kombinacji zamówień. Algorytm wypełnia tablicę iteracyjnie, zwracając uwagę na możliwą ilość wykonanych zamówień i czas ich wykonania (pod warunkiem braku utraty większej liczby wykonanych zamówień), wybierając najbardziej optymalną kombinacje.

Ostatecznie, ostatnia komórka tabeli zawiera wynikowe zamówienia przydzielone danemu pracownikowi, a następnie generowane są zadania (List<Task> tasks) na podstawie tego wyniku.

Dla kolejnych pracowników, uprzednio dobrane zamówienia nie są brane pod uwagę. Tym sposobem algorytm stara się rozdzielić pracownikom jak największą liczbę zamówień w dostępnym czasie i w terminach ich wykonania.

Podczas implementacji zadania, użyłam Gradle.
