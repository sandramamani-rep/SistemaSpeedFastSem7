![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)

# 🚚 SpeedFast — Gestión de pedidos y entregas

## 👤 Autor del proyecto

- **Nombre completo:** Sandra Mamani
- **Carrera:** Analista Programador Computacional
- **Sede:** Online

---

## 📘 Descripción general del sistema

Este proyecto corresponde a la actividad formativa de la semana 7 de la asignatura *Desarrollo Orientado a Objetos II*. Es una aplicación de escritorio desarrollada en Java para gestionar pedidos, repartidores y entregas de la empresa SpeedFast, conectándose a una base de datos MySQL mediante JDBC.

La aplicación permite registrar pedidos y repartidores, consultar sus registros en tablas gráficas y asignar un repartidor a un pedido. Las entregas registran la fecha y hora, y quedan asociadas al pedido y al repartidor mediante claves foráneas. De esta manera, la información permanece guardada en la base de datos al cerrar la aplicación.

---

## ✅ Funcionalidades principales

- Registrar pedidos indicando dirección, tipo y estado.
- Consultar pedidos almacenados en MySQL mediante un `JTable`.
- Registrar repartidores y consultar sus datos.
- Seleccionar un pedido y un repartidor en la pestaña **Entregas**.
- Registrar una entrega con fecha y hora.
- Consultar las entregas existentes, incluyendo pedido, dirección y repartidor asociados.
- Conectar Java con MySQL utilizando JDBC, `DriverManager`, `PreparedStatement` y `ResultSet`.

---

## 🧱 Estructura general del proyecto

```text
📁 SistemaSpeedFastSem7/
├── 📁 lib/
│   └── mysql-connector-j-26.7.0.jar
├── 📁 src/
│   ├── 📁 controlador/
│   │   ├── ControladorEntrega.java
│   │   ├── ControladorPedido.java
│   │   └── ControladorRepartidores.java
│   ├── 📁 dao/
│   │   ├── ConexionBD.java
│   │   ├── EntregaDAO.java
│   │   ├── PedidoDAO.java
│   │   └── RepartidorDAO.java
│   ├── 📁 main/
│   │   └── Main.java
│   ├── 📁 modelo/
│   │   ├── Entrega.java
│   │   ├── EstadoPedido.java
│   │   ├── Pedido.java
│   │   ├── Repartidor.java
│   │   └── TipoPedido.java
│   ├── 📁 sql/
│   │   └── speedfast_db.sql
│   └── 📁 vista/
│       ├── VentanaPrincipal.java
│       └── VentanaPrincipal.form
├── README.md
└── SistemaSpeedFastSem7.iml
```

### Responsabilidad de los paquetes

- **controlador:** valida las solicitudes de la interfaz y coordina las operaciones.
- **dao:** administra la conexión JDBC y las consultas a MySQL.
- **main:** contiene el punto de entrada de la aplicación.
- **modelo:** representa pedidos, repartidores, entregas y sus tipos o estados.
- **sql:** contiene el script de creación de la base de datos y sus tablas.
- **vista:** contiene la interfaz gráfica Swing y su formulario de IntelliJ.

---

## 🗃️ Base de datos

El proyecto utiliza la base de datos `speedfast_db`, con las tablas `pedido`, `repartidor` y `entrega`. La tabla `entrega` contiene las claves foráneas que la relacionan con un pedido y un repartidor.

El script de creación se encuentra en:

```text
src/sql/speedfast_db.sql
```

Para crear el esquema, abre el archivo en MySQL Workbench o en un cliente MySQL y ejecuta el script. Si ya existen las tablas, revisa la base antes de volver a ejecutar los comandos `CREATE TABLE`.

---

## ⚙️ Requisitos

- Java JDK 17.
- IntelliJ IDEA.
- MySQL Server en ejecución.
- Conector JDBC de MySQL, incluido en `lib/mysql-connector-j-26.7.0.jar`.

---

## 🔌 Configurar conexión a MySQL

La configuración de conexión está en:

```text
src/dao/ConexionBD.java
```

Antes de ejecutar, comprueba que la URL apunte a tu instancia local y que el nombre de la base sea `speedfast_db`. Configura el usuario y la contraseña que utilizas en MySQL en esa clase. **No subas credenciales reales a un repositorio público**; para compartir el proyecto, es preferible leerlas desde variables de entorno.

En IntelliJ, verifica que el conector `mysql-connector-j-26.7.0.jar` esté agregado como dependencia del módulo. Si IntelliJ no lo reconoce automáticamente, agrégalo desde:

```text
File > Project Structure > Modules > Dependencies > + > JARs or Directories
```

Selecciona el JAR ubicado en la carpeta `lib/` y aplica los cambios.

---

## ▶️ Instrucciones para abrir y ejecutar

1. Clona el repositorio nuevo:

```bash
git clone https://github.com/sandramamani-rep/SistemaSpeedFastSem7.git
```

2. Abre en IntelliJ IDEA la carpeta `SistemaSpeedFastSem7`.
3. Selecciona JDK 17 como SDK del proyecto.
4. Ejecuta el script `src/sql/speedfast_db.sql` en MySQL para crear la base de datos y sus tablas.
5. Revisa los datos de conexión en `src/dao/ConexionBD.java` y verifica que el conector MySQL esté agregado al módulo.
6. Ejecuta `src/main/Main.java`.
7. Registra pedidos y repartidores. En la pestaña **Entregas**, selecciona ambos y registra la entrega.

---

## 🖥️ Uso de la aplicación

1. En **Pedido**, ingresa la dirección, el tipo y el estado, y registra el pedido.
2. En **Repartidores**, ingresa el nombre y registra al repartidor.
3. En **Entregas**, elige un pedido y un repartidor y presiona el botón para registrar la entrega.
4. La tabla de entregas muestra el ID, pedido, dirección, repartidor, fecha y hora.

---

## 🔗 Repositorio y entrega

- **Repositorio GitHub:** [\[link\]](https://github.com/sandramamani-rep/SistemaSpeedFastSem7.git)
- **Fecha de entrega:** [28/09/2026]

---

© Duoc UC | Escuela de Informática y Telecomunicaciones | Actividad Semana 7
