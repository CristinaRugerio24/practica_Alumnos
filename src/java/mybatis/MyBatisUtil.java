package mybatis;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
    public static String RESOURCE = "mybatis/mybatis-config.xml"; 
    public static String ENVIROMENT= "development";//IDentidificar los elementos que esta trabajando
    
    public static SqlSession getSession(){//regresar sql para poder trabajar con conexiones de bd
        //Crear el objeto SQL 
        SqlSession session=null; 
        try {
            Reader reader=Resources.getResourceAsReader(RESOURCE);
            //La información que requiero cargar, para poder ser leido y pueda crearse la conexion
            SqlSessionFactory sqlMapper= new SqlSessionFactoryBuilder().build(reader,ENVIROMENT);
             //Crea un objeto solo con lo que quieras remplazar y lo demás lo toma por defecto
             //Inicializamos la sesión
             session=sqlMapper.openSession();
  
            
            
        } catch (IOException e) { //Execpción de entrada y salida
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        
        
        
        return session;
    }
}
