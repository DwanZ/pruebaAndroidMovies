# pruebaAndroidMovies

Ya que utilicé el patrón mvp clean las capas que se pueden apreciar claramente son las de modelo, vista, presentador los paquetes funcionalidad a los que ago mención más adelante son popular topRated, upcoming y movieDetail.

Modelo:
El paquete data contiene las entidades del negocio movies y movie, los datasource que son interfaces que contienen los métodos que son implementados por los repositorios de datos las clases repositorios están enfocadas en contener las n consultas de una entidad de negocio. Los repositorios no son suceptibles a la fuente de los datos que consultan, en este caso el repositorio solo tiene una fuente de datos que es la remota pero en otros proyectos tiene una local también.

Presentador:
Es la capa intermedia que conecta las vistas con el modelo, cada paquete funcionalidad contiene un presenter por fragmento y un caso de uso que representa la lógica de una acción que requiera hacer una consulta a una fuente de datos y/o lógica de negocio sobre la misma información, esto asegura que los presentadores reciban la información ya tratada y se la entreguen directamente a la vista para ser mostrada al usuario, los presentadores también capturan las acciones que realice un usuario en la vista.

Vista:
Los paquetes splash e introPager contienen solo actividades y clases de lógica para darle un poco de vida a la app, cada paquete funcionalidad contiene una actividad y fragmentos que hacen parte de esta capa y representan las vistas con las que interactua el usuario, las actividades se encargan de inicializar los casos de uso y los presentadores e inflar los fragmentos, los fragmentos se encargan de interactuar con el presentador y mostrar las respuestas a las acciones del usuario.

1. En qué consiste el principio de responsabilidad única? Cuál es su propósito? 
R/= El principio de responsabilidad única dice que cada clase, objeto, servicio, módulo etc. debe manejar una única responsabilidad o propósito, con esto se refiere a que si se crea la clase película, esta debe estar destinada para realizar el manejo de la información de una película. Su propósito además de la organización es no sobrecargar clases u objetos con muchas funcionalidades si no estructurarlas de una mejor manera, siendo así una clase con muchas responsabilidades tiende a ser modificada muchas veces y esto impacta en su mantenimiento a futuro.

2. Qué características tiene, según su opinión, un “buen” código o código limpio? 
R/= En mi opinión un buen código debe ser ordenado, que se pueda leer, identado, las funciones y variables bien nombradas, es decir, sin necesidad de comentarios entenderse que hace un método y su lógica.