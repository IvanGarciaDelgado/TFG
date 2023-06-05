package es.ivangd.configuracion;

import es.ivangd.entidades.*;
import es.ivangd.repositorios.DietaRepository;
import es.ivangd.repositorios.ProductoRepository;
import es.ivangd.repositorios.RoleRepository;
import es.ivangd.servicios.CompraService;
import es.ivangd.servicios.ProductoService;
import es.ivangd.servicios.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;


@Slf4j // Es una librería Java que funciona como una abstracción simple para varios frameworks de logging, para poder añadir la notación log.info
@EnableJpaAuditing // Sirve para las clases configuration para inicializar las auditorias y que sean funcionales.
@Configuration
public class InitialDataConfiguration implements CommandLineRunner {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CompraService compraService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DietaRepository dietaRepository;


    @Autowired
    public InitialDataConfiguration(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {

        // Comprueba si el rol "ROLE_USER" ya existe en el repositorio de roles
        if (!roleRepository.existsByNombre("ROLE_USER")) {
            // Si no existe, crea un nuevo objeto Role y establece su nombre como "ROLE_USER"
            Role roleUser = new Role();
            roleUser.setNombre("ROLE_USER");

            // Guarda el nuevo objeto Role en el repositorio de roles
            roleRepository.save(roleUser);
        }

        // Comprueba si el rol "ROLE_ADMIN" ya existe en el repositorio de roles
        if (!roleRepository.existsByNombre("ROLE_ADMIN")) {
            // Si no existe, crea un nuevo objeto Role y establece su nombre como "ROLE_ADMIN"
            Role roleAdmin = new Role();
            roleAdmin.setNombre("ROLE_ADMIN");

            // Guarda el nuevo objeto Role en el repositorio de roles
            roleRepository.save(roleAdmin);
        }
    }



    @Bean
    public void initProductos() {

        productoRepository.deleteAll();


        Producto producto = new Producto("Proteina de vainilla", 39.9f, "/img/producto1.jpg", 29, "Nuestra proteína de vainilla se destaca por su alta calidad y contenido de proteínas premium, como proteína de suero de leche. Con un perfil completo de aminoácidos, apoya la reparación y el crecimiento muscular. Además, su absorción rápida garantiza que tu cuerpo obtenga los nutrientes esenciales necesarios para la recuperación y el desarrollo de masa magra. Disfruta de su suave y delicioso sabor a vainilla mientras trabajas para alcanzar tus objetivos de fitness. Además, puedes estar seguro de su calidad, ya que ha sido sometida a rigurosos controles de calidad para garantizar pureza y seguridad.", "/img/proteinaVainilla2.png", "/img/proteinaVainilla3.jpg", "/img/myProteinDetalles.png","myprotein");

        Producto producto1 = new Producto("Proteina de chocolate", 39.9f, "/img/producto2.jpg",29, "Nuestra proteína de chocolate se distingue por su alta calidad y contenido de proteínas premium, como proteína de suero de leche. Con un perfil completo de aminoácidos, apoya la reparación y el crecimiento muscular. Además, su absorción rápida garantiza que tu cuerpo obtenga rápidamente los nutrientes esenciales y los aminoácidos necesarios para la recuperación muscular y el desarrollo de masa magra. Disfruta de su delicioso sabor a chocolate mientras trabajas para alcanzar tus objetivos de fitness. Además, puedes confiar en su calidad, ya que ha sido sometida a rigurosos controles de calidad para garantizar pureza y seguridad.", "/img/proteinaChocolate2.png", "/img/proteinaChocolate3.jpg", "/img/myProteinDetalles.png","myprotein");

        Producto producto2 = new Producto("Life Fitness T3 Con Consola Go", 3489.9f, "/img/cintaCorrer2.jpg",20, "La cinta de correr Life Fitness T3 con Consola Go es ideal para alcanzar tus metas de fitness. Con un diseño duradero, te brinda un entrenamiento de alta calidad. Ajusta la velocidad y la inclinación según tus necesidades. La Consola Go ofrece programas personalizados y monitorea tus estadísticas en tiempo real. Disfruta de sesiones efectivas en casa, protegiendo tus articulaciones. Confía en la calidad de Life Fitness para llevar tu condición física al siguiente nivel.", "/img/cintaCorrer.jpg", "/img/cintaCorrer3.jpg", "/img/lifeFitnessDetalles.jpg","life fitness");

        Producto producto3 = new Producto("Snack proteico", 3.9f, "/img/producto3.jpg",6, "Nuestras barritas proteicas de chocolate Prozis son el snack perfecto para satisfacer tus antojos mientras cuidas tu salud y alcanzas tus metas fitness. Cada barrita está repleta de proteínas de alta calidad, proporcionando un impulso de energía y ayudando en la reparación y desarrollo muscular. El irresistible sabor a chocolate te permitirá disfrutar de un momento delicioso sin sacrificar tus objetivos de nutrición. Con la confianza de la marca Prozis, nuestras barritas proteicas de chocolate han sido cuidadosamente elaboradas para brindarte una opción de calidad y sabor excepcionales. ¡Disfruta de un snack nutritivo y sabroso con nuestras barritas proteicas de chocolate myProtein", "/img/snackProtein2.jpg", "/img/snackProtein3.jpg", "/img/myProteinDetalles.png", "Prozis");

        Producto producto4 = new Producto("Bicicleta Ciclismo Indoor IC2", 950.9f, "/img/bicicletaCiclismo.png",4, "La bicicleta de ciclismo indoor Life Fitness IC2 te brinda una experiencia de entrenamiento de alta calidad. Ajusta la resistencia, disfruta de un pedaleo suave y silencioso. El manillar y el asiento ajustables garantizan comodidad. La consola te proporciona información en tiempo real. Obtén desafiantes entrenamientos en casa con la durabilidad y confiabilidad de Life Fitness.", "/img/bicicletaCiclismo2.png", "/img/bicicletaCiclismo3.png", "/img/lifeFitnessDetalles.jpg", "life fitness");

        Producto producto5 = new Producto("Elíptica E3 Track Connect", 3200.0f, "/img/eliptica.png",0, "La bicicleta elíptica Life Fitness E3 Track Connect es perfecta para tus entrenamientos en casa. Con resistencia ajustable y movimiento elíptico suave, ofrece un ejercicio de bajo impacto. Con la tecnología Track Connect, accede a programas personalizados. Disfruta de entrenamientos completos con calidad Life Fitness.", "/img/eliptica2.png", "/img/eliptica3.png", "/img/lifeFitnessDetalles.jpg", "life fitness");

        Producto producto6 = new Producto("Monohidrato de creatina", 23.99f, "/img/creatina.png",10, "El monohidrato de proteína de Prozis es una opción de alta calidad para tus necesidades proteicas. Fácil de mezclar y disponible en varios sabores, es ideal para la recuperación muscular y el crecimiento. Confía en Prozis y maximiza tus resultados fitness.", "/img/creatina2.png", "/img/creatina3.png", "/img/prozisDetalles.png", "Prozis");

        Producto producto7 = new Producto("Omega 3 capsulas", 8.9f, "/img/omega3.png",0, "Las cápsulas de Omega 3 de Prozis son una opción confiable y conveniente para asegurar la ingesta adecuada de ácidos grasos esenciales en tu dieta diaria. Cada envase contiene 90 cápsulas, proporcionando un suministro duradero de este importante nutriente. El Omega 3, compuesto por ácidos grasos EPA y DHA, desempeña un papel crucial en diversos aspectos de la salud. Estos ácidos grasos esenciales son reconocidos por sus propiedades antiinflamatorias, que pueden ayudar a reducir la inflamación en el cuerpo y a mantener la salud cardiovascular.", "/img/omega3dos.png", "/img/omega3tres.png", "/img/prozisDetalles.png", "Prozis");

        Producto producto8 = new Producto("Pre-workout", 29.99f, "/img/preWorkout.png",24, "Big Shot - Pre-Workout 23 de Prozis es un potente pre-entreno diseñado para aumentar tu energía y rendimiento durante los entrenamientos. Con ingredientes de alta calidad, te proporciona un impulso de energía y mejora la concentración. Eleva tu rendimiento y alcanza tus metas fitness con Big Shot de Prozis.", "/img/preWorkout2.png", "/img/preWorkout3.png", "/img/prozisDetalles.png", "Prozis");

        Producto producto9 = new Producto("Skillrow", 3999.99f, "/img/remo.jpg",19, "El Skillrow de Technogym es una máquina de remo de alta calidad diseñada para un entrenamiento completo y realista. Con resistencia ajustable y tecnología avanzada, te brinda resultados efectivos en tu condición física. Confía en Technogym para obtener un rendimiento excepcional en cada remada.", "/img/remo2.jpg", "/img/remo3.png", "/img/TechnogymDetalle.png", "technogym");

        Producto producto10 = new Producto("Proteína On 100% Whey Gold Standard", 34.99f, "/img/optimumProteina.jpg",19, "La proteína ON 100% Whey Gold Standard de Optimum Nutrition es una opción de alta calidad para tus necesidades proteicas. Con una mezcla de proteínas de suero de leche premium, favorece el desarrollo y recuperación muscular. Confía en Optimum Nutrition para obtener resultados óptimos en tu entrenamiento.", "/img/optimumProteina2.jpg", "/img/optimumProteina3.jpg", "/img/optimumDetalles.jpg", "optimum nutrition");

        Producto producto11 = new Producto("Creatina optimum", 34.99f, "/img/creatinaOptimum.jpg",19, "La creatina de Optimum Nutrition es un suplemento confiable y efectivo para mejorar tu fuerza y rendimiento. Promueve la producción de energía muscular y acelera la recuperación. Confía en Optimum Nutrition para alcanzar tus metas fitness.", "/img/creatinaOptimum2.jpg", "/img/creatinaOptimum3.jpg", "/img/optimumDetalles.jpg", "optimum nutrition");

        Producto producto12 = new Producto("Gainer mass", 14.99f, "/img/gainerMass.jpg",0, "El gainer Mass de Optimum Nutrition es ideal para aumentar la masa muscular y alcanzar tus objetivos de volumen. Con una combinación de carbohidratos y proteínas de calidad, brinda los nutrientes necesarios para el crecimiento muscular. Confía en Optimum Nutrition para obtener resultados excepcionales en tu entrenamiento.", "/img/gainerMass2.jpg", "/img/gainerMass3.jpg", "/img/optimumDetalles.jpg", "optimum nutrition");

        Producto producto13 = new Producto("Maquina multifuncional", 4999.99f, "/img/multiFuncional.jpg",9, "La máquina multifuncional de Technogym es la opción completa para tu entrenamiento. Con múltiples estaciones y accesorios, te permite trabajar diferentes grupos musculares de manera eficiente. Confía en Technogym para obtener resultados óptimos en cada ejercicio.", "/img/multiFuncional2.jpg", "/img/multiFuncional3.jpg", "/img/TechnogymDetalle.png", "technogym");

        Producto producto14 = new Producto("Rack", 3499.99f, "/img/rack.jpg",9, "El rack de 5 líneas de Technogym es una solución robusta y versátil para el almacenamiento ordenado de discos y barras en tu área de entrenamiento. Con su diseño duradero y resistente, proporciona un soporte confiable para tus rutinas de levantamiento de pesas, al mismo tiempo que maximiza el espacio disponible. Confía en Technogym para obtener un rack de almacenamiento de pesas de alta calidad y mantener tu gimnasio o estudio personal organizado y libre de desorden.", "/img/rack2.jpg", "/img/rack3.jpg", "/img/TechnogymDetalle.png", "technogym");

        Producto producto15 = new Producto("Bicicleta estática reclinada", 2600.99f, "/img/bicicletaMatrix.jpg",0, "La bicicleta reclinada Matrix R50 es una opción cómoda y eficiente para tus sesiones de ejercicio. Con su diseño ergonómico y asiento ajustable, te brinda un apoyo adecuado para tu espalda y garantiza una postura cómoda durante tus entrenamientos. Equipada con programas de entrenamiento personalizables y una pantalla intuitiva, la bicicleta reclinada Matrix R50 te permite monitorear tu progreso y mantener un seguimiento de tu rendimiento. Confiabilidad, comodidad y funcionalidad se combinan en esta bicicleta reclinada de Matrix, proporcionándote una experiencia de entrenamiento excepcional en tu propio hogar o en el gimnasio.", "/img/bicicletaMatrix2.jpg", "/img/bicicletaMatrix3.jpg", "/img/matrixDetalles.jpg", "matrix");

        Producto producto16 = new Producto("Eliptica Ascent A50", 5000.00f, "/img/eliptica.Matrix.jpg",5, "La elíptica Ascent A50 es una opción versátil y eficaz para tu entrenamiento cardiovascular. Con su diseño ergonómico y movimiento suave, te brinda una experiencia cómoda y de bajo impacto en cada sesión de ejercicio. Equipada con características como múltiples niveles de resistencia y programas de entrenamiento preestablecidos, la elíptica Ascent A50 te permite personalizar y desafiar tu rutina según tus necesidades y metas. Además, su pantalla intuitiva y conectividad Bluetooth te permite monitorear tu progreso y sincronizar tus datos con aplicaciones de fitness. Confía en la calidad y rendimiento de la elíptica Ascent A50 para obtener un entrenamiento completo y efectivo en la comodidad de tu hogar o en el gimnasio.", "/img/eliptica.Matrix2.jpg", "/img/eliptica.Matrix3.jpg", "/img/matrixDetalles.jpg", "matrix");

        Producto producto17 = new Producto("Bicicleta estatica vertical", 1299.99f, "/img/bicicletaVertical.jpg",40, "La bicicleta estática vertical U30 es una opción práctica y eficiente para tu entrenamiento cardiovascular en casa. Con su diseño compacto y vertical, ocupa menos espacio y se adapta fácilmente a cualquier ambiente. Equipada con un asiento ajustable y manillares ergonómicos, te brinda comodidad y soporte durante tus sesiones de ejercicio. La bicicleta estática U30 cuenta con múltiples niveles de resistencia para que puedas personalizar la intensidad de tu entrenamiento. Además, su pantalla intuitiva te brinda información en tiempo real sobre la distancia, el tiempo, las calorías quemadas y mucho más. Confía en la calidad y durabilidad de la bicicleta estática vertical U30 para alcanzar tus metas de acondicionamiento físico desde la comodidad de tu hogar.", "/img/bicicletaVertical2.jpg", "/img/bicicletaVertical3.jpg", "/img/matrixDetalles.jpg", "matrix");

        Producto producto18 = new Producto("Clear muscle", 34.99f, "/img/clearMuscle.png",15, "Clear Muscle de MuscleTech es un suplemento avanzado diseñado para ayudarte a maximizar tu rendimiento y desarrollar masa muscular magra. Con su fórmula única y de alta calidad, Clear Muscle proporciona una dosis clínicamente estudiada de BetaTOR, un ingrediente clave que ha demostrado potenciar la síntesis de proteínas y promover la recuperación muscular. Este suplemento de MuscleTech está diseñado para ser absorbido rápidamente por el cuerpo, lo que significa que puedes experimentar sus beneficios de manera más eficiente. Clear Muscle te brinda el apoyo necesario para superar tus límites en el gimnasio y alcanzar tus objetivos de fuerza y tamaño muscular.", "/img/clearMuscle2.png", "/img/clearMuscle3.png", "/img/muscleTechDetalle.png", "muscletech");

        Producto producto19 = new Producto("Burn IQ", 13.99f, "/img/burnIq.png",0, "Burn IQ de MuscleTech es un suplemento revolucionario y altamente efectivo diseñado específicamente para apoyar tus esfuerzos en la quema de grasa y alcanzar una definición muscular óptima. Esta fórmula avanzada combina una variedad de ingredientes clave cuidadosamente seleccionados para brindarte un enfoque integral en tu transformación física. El Burn IQ de MuscleTech contiene extracto de té verde, un potente antioxidante conocido por sus propiedades termogénicas y su capacidad para aumentar el metabolismo. Esto significa que tu cuerpo estará en un estado de quema de calorías más eficiente tanto durante tus entrenamientos como en reposo.", "/img/burnIq2.png", "/img/burnIq3.png", "/img/muscleTechDetalle.png", "muscletech");

        Producto producto20 = new Producto("Proteina Gold", 59.99f, "/img/wheyGold.png",0, "Nitro Tech 100% Whey Gold de MuscleTech es un suplemento de proteína de suero de alta calidad diseñado para promover el desarrollo muscular magro. Con una fórmula avanzada y de rápida absorción, este producto proporciona una dosis óptima de proteína de suero para respaldar la síntesis de proteínas musculares y mejorar la recuperación después del entrenamiento. Con un perfil bajo en grasas y carbohidratos, Nitro Tech 100% Whey Gold es ideal para aquellos que buscan una fuente de proteínas pura y efectiva. Además, su delicioso sabor y fácil mezcla hacen que sea un placer consumirlo. Confía en MuscleTech y disfruta de los beneficios de una proteína de suero de calidad superior con Nitro Tech 100% Whey Gold.", "/img/wheyGold2.png", "/img/wheyGold3.png", "/img/muscleTechDetalle.png", "muscletech");

        Producto producto21 = new Producto("Treadmill 950", 4550.00f, "/img/treadmill.png",10, "La cinta de correr 950 de True Fitness es una máquina de alto rendimiento diseñada para ofrecerte una experiencia excepcional de entrenamiento. Con su potente motor, velocidades ajustables y amplia superficie de carrera, te brinda un ejercicio cardiovascular efectivo y cómodo. Además, cuenta con programas preestablecidos, una pantalla intuitiva y funciones de entretenimiento para que puedas monitorear tu progreso y disfrutar de tus entrenamientos al máximo. Confía en la calidad y el rendimiento de la cinta de correr 950 de True Fitness para alcanzar tus objetivos de acondicionamiento físico de manera eficiente y conveniente.", "/img/treadmill2.png", "/img/treadmill3.png", "/img/trueFitnessDetalle.jpg", "truefitness");

        Producto producto22 = new Producto("Bicicleta ES700 RECUMBENT", 2999.99f, "/img/recumbent.png",25, "La bicicleta ES700 Recumbent de True Fitness combina comodidad y rendimiento en un solo equipo de entrenamiento. Diseñada con un asiento reclinado y ajustable, esta bicicleta ofrece un soporte óptimo para la espalda y las articulaciones, permitiéndote ejercitarte durante largos periodos sin incomodidad. Con su sistema de resistencia magnética suave y silencioso, puedes ajustar la intensidad de tu entrenamiento según tus necesidades. La consola avanzada te brinda datos en tiempo real, como la distancia, el tiempo y las calorías quemadas, para que puedas realizar un seguimiento preciso de tu progreso. Además, cuenta con características adicionales, como un soporte para tablet y altavoces integrados, para que puedas disfrutar de entretenimiento mientras te ejercitas. Confía en la calidad y el diseño duradero de la bicicleta ES700 Recumbent de True Fitness para disfrutar de entrenamientos cómodos y efectivos que te ayudarán a alcanzar tus metas de acondicionamiento físico.", "/img/recumbent2.png", "/img/recumbent3.png", "/img/trueFitnessDetalle.jpg", "truefitness");

        Producto producto23 = new Producto("Eliptica M30", 3099.99f, "/img/elipticam30.png",5, "La elíptica M30 de True Fitness es una máquina de ejercicio de alta calidad que te brinda una experiencia de entrenamiento eficiente y cómoda. Con su sistema de resistencia magnética suave y silencioso, puedes ajustar la intensidad de tu entrenamiento según tus necesidades y nivel de condición física. Su consola intuitiva te permite seleccionar entre una variedad de programas de entrenamiento preestablecidos y monitorear tu rendimiento en tiempo real, incluyendo la distancia recorrida, el tiempo transcurrido y las calorías quemadas. Además, su diseño ergonómico y movimiento de bajo impacto hacen que sea una opción ideal para personas de todas las edades y niveles de condición física. Confía en la calidad y rendimiento de la elíptica M30 de True Fitness para alcanzar tus metas de acondicionamiento físico y disfrutar de un entrenamiento efectivo en la comodidad de tu hogar o en el gimnasio.", "/img/elipticam302.png", "/img/elipticam30.png", "/img/trueFitnessDetalle.jpg", "truefitness");




        productoRepository.save(producto);
        productoRepository.save(producto1);
        productoRepository.save(producto2);
        productoRepository.save(producto3);
        productoRepository.save(producto4);
        productoRepository.save(producto5);
        productoRepository.save(producto6);
        productoRepository.save(producto7);
        productoRepository.save(producto8);
        productoRepository.save(producto9);
        productoRepository.save(producto10);
        productoRepository.save(producto11);
        productoRepository.save(producto12);
        productoRepository.save(producto13);
        productoRepository.save(producto14);
        productoRepository.save(producto15);
        productoRepository.save(producto16);
        productoRepository.save(producto17);
        productoRepository.save(producto18);
        productoRepository.save(producto19);
        productoRepository.save(producto20);
        productoRepository.save(producto21);
        productoRepository.save(producto22);
        productoRepository.save(producto23);
    }

    @Bean
    public List<Dieta> createDietas(DietaRepository dietaRepository, ProductoRepository productoRepository) {

        dietaRepository.deleteAll();

        List<Dieta> dietas = new ArrayList<>();

        Producto producto = productoRepository.findFirstByNombre("Burn IQ");
        Producto producto2 = productoRepository.findFirstByNombre("Snack proteico");
        Producto producto3 = productoRepository.findFirstByNombre("Clear muscle");
        Producto producto4 = productoRepository.findFirstByNombre("Proteína On 100% Whey Gold Standard");


        Dieta dieta1 = new Dieta("VitaBalance1500", 1500, "VitaBalance1500 es una dieta equilibrada que promueve la salud y el bienestar a través de una combinación de alimentos nutritivos y deliciosos. Esta dieta te ayuda a mantener un equilibrio adecuado de nutrientes esenciales para una vida saludable.");
        dieta1.addAlimento("Proteínas: pollo, pescado, tofu");
        dieta1.addAlimento("Vegetales: espinacas, zanahorias, brócoli.");
        dieta1.addAlimento("Grasas saludables: aguacate, nueces, aceite de oliva.");
        dieta1.addAlimento(producto.getNombre());
        dietas.add(dieta1);

        Dieta dieta2 = new Dieta("VitaFuel2000", 2000, "VitaFuel2000 es una dieta diseñada para proporcionar una nutrición óptima y satisfacer tus necesidades diarias de energía con una ingesta de 2000 calorías. Esta dieta equilibrada y saludable te ayudará a mantener un estilo de vida activo y alcanzar tus metas de bienestar.");
        dieta2.addAlimento("Granos integrales: arroz integral, quinoa, pasta de trigo integral.");
        dieta2.addAlimento("Lácteos bajos en grasa: yogur griego, leche desnatada, queso cottage.");
        dieta2.addAlimento("Legumbres: lentejas, garbanzos, frijoles pintos.");
        dieta2.addAlimento(producto2.getNombre());
        dietas.add(dieta2);

        Dieta dieta3 = new Dieta("PowerUp3000", 3000, "PowerUp3000 es una dieta diseñada específicamente para personas con necesidades calóricas más altas, proporcionando una ingesta diaria de 3000 calorías. Esta dieta equilibrada y energética te ayudará a mantener niveles óptimos de energía y alcanzar tus objetivos de fuerza y rendimiento físico.");
        dieta3.addAlimento("Snacks saludables: almendras, mantequilla de maní, batidos proteicos, barras energéticas.");
        dieta3.addAlimento("Frutos secos y mantequillas de nueces: almendras, nueces, cacahuetes, mantequilla de almendras o de cacahuete.");
        dieta3.addAlimento("Carnes más grasas: cerdo, cordero, salchichas, tocino.");
        dieta3.addAlimento(producto3.getNombre());
        dietas.add(dieta3);

        Dieta dieta4 = new Dieta("EnergeticFuel4000", 4000, "EnergeticFuel4000 es una dieta especialmente diseñada para atletas o personas con altas demandas energéticas, proporcionando una ingesta diaria de 4000 calorías. Esta dieta rica en nutrientes y calorías te ayudará a mantener niveles óptimos de energía, apoyar el crecimiento muscular y alcanzar tus metas de rendimiento físico.");
        dieta4.addAlimento("Fuentes de carbohidratos complejos: arroz integral, pasta de trigo integral, patatas, quinoa, avena.");
        dieta4.addAlimento("Pescados grasos: salmón, trucha, sardinas, atún en aceite.");
        dieta4.addAlimento("Frutas más calóricas: aguacate, coco, dátiles, uvas pasas.");
        dieta4.addAlimento(producto4.getNombre());
        dietas.add(dieta4);

        return dietaRepository.saveAll(dietas);
    }

}