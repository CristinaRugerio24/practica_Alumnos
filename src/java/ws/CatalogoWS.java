package ws;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.*; 

@Path("catalogo")
public class CatalogoWS {

    @Context
    private UriInfo context;

    public CatalogoWS() {
 
    }
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> gettAllUser(){
        List<Usuario> usuarioBD= new ArrayList<>(); 
        SqlSession conn= MyBatisUtil.getSession();
        if(conn !=null){
            try {
               usuarioBD=conn.selectList("Usuario.getAllUsuarios");
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conn.close();
            }
        }
        return usuarioBD;
    } 
    
    @Path("byId/(idUsuario)")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUserById (@PathParam("idUsuario") Integer idUsuario){
        Usuario user=null; 
        SqlSession conn= MyBatisUtil.getSession(); 
        if(conn != null){
            try{
                user=conn.selectOne("Usuario.getUsuarioById",idUsuario);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conn.close(); 
            }
        }
        
        return user; 
    }
    
   
    @Path("byId/(idUsuario),(nombre)")
    @GET
    //Sobre la URL
    @Produces(MediaType.APPLICATION_JSON)
    //Pasar información de la URL al codigo
    public Usuario getUserById(@PathParam("idUsuario")Integer idUsuario,@PathParam("nombre")String nombre){
        Usuario user=new Usuario(idUsuario,nombre,"admin","12345a"); 
        return user; 
    }
    
    @Path("registrar")
    @POST
    //Usar URL o el cuerpo del mensaje, trabaja con la ruta y el retorno 
    @Produces (MediaType.APPLICATION_JSON)
    //Los parametros tienen que adaptarse el cliente al nombre asignado
    public Mensaje registrarUsuario(@FormParam("nombre") String nombre,
                                    @FormParam("username") String username,
                                    @FormParam("password")String password){
    
        Usuario user=new Usuario(0,nombre,username,password);
        Mensaje mensaje=null; 
        SqlSession conn=MyBatisUtil.getSession();
        if(conn!=null){
            try {
                int numFilas=conn.insert("Usuario.registrar",user);
                //Se realiza un comit para todas las operacioines con insersion,actualización o eliminación
                conn.commit();
                if(numFilas>0){
                    mensaje=new Mensaje("Usuario guardado con exito",false);
                }else{
                    mensaje=new Mensaje("Lo sentimos el usuario no se pudo registrar",true);
                }
            } catch (Exception e) {
                mensaje=new Mensaje("Error"+e.getMessage(),true);
            }finally{
                //Sirve para cerrar la conexion el finally 
                conn.close();
            }
        }
       return mensaje;
    }
    
    @Path("actualizar")
    @PUT
    //Usar URL o el cuerpo del mensaje, trabaja con la ruta y el retorno 
    @Produces (MediaType.APPLICATION_JSON)
    //Los parametros tienen que adaptarse el cliente al nombre asignado
    public Mensaje actualizarUsuario(@FormParam("idUsuario") Integer idUsuario, 
                                    @FormParam("nombre") String nombre,
                                    @FormParam("username") String username,
                                    @FormParam("password")String password){
        Usuario user=new Usuario(idUsuario,nombre,username,password);
        Mensaje mensaje=null; 
        SqlSession conn=MyBatisUtil.getSession();
        if(conn!=null){
            try {
                int numFilas=conn.update("Usuario.editarUsuario",user);
                //Se realiza un comit para todas las operacioines con insersion,actualización o eliminación
                conn.commit();
                if(numFilas>0){
                    mensaje=new Mensaje("Usuario"+user.getNombre()+"actualizar con exito", false); 
                }else{
                    mensaje=new Mensaje("Lo sentimos el usuario no se pudo registrar",true);
                }
            } catch (Exception e) {
                mensaje=new Mensaje("Error"+e.getMessage(),true);
            }finally{
                //Sirve para cerrar la conexion el finally 
                conn.close();
            }
        }
        return mensaje; 
    }
    @Path("eliminar/{idUsuario}")
    @DELETE
    @Produces (MediaType.APPLICATION_JSON)
    public Mensaje eliminarUsuario(@PathParam("idUsuario") Integer idUsuario){
        SqlSession conn=MyBatisUtil.getSession();
        Mensaje mensaje = null;
        if(conn!=null){
            try {
                int numFilas=conn.delete("Usuario.eliminar",idUsuario);
                //Se realiza un comit para todas las operacioines con insersion,actualización o eliminación
                conn.commit();
                if(numFilas>0){
                    mensaje=new Mensaje("Usuario"+idUsuario+"eliminado con exito", false); 
                }else{
                    mensaje=new Mensaje("Lo sentimos el usuario no se pudo eliminar",true);
                }
            } catch (Exception e) {
                mensaje=new Mensaje("Error"+e.getMessage(),true);
            }finally{
                //Sirve para cerrar la conexion el finally 
                conn.close();
            }
        }
        return mensaje; 
    }
    
    
    @Path("byId/(idFacultad)")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Facultad getFacultadById (@PathParam("idFacultad") Integer idFacultad){
        Facultad facultad=null; 
        SqlSession conn= MyBatisUtil.getSession(); 
        if(conn != null){
            try{
                facultad=conn.selectOne("Facultad.getFacultadById",idFacultad);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conn.close(); 
            }
        }
        return facultad; 
    }
    
   
    @Path("byId/(idFacultad),(nombre)")
    @GET
    //Sobre la URL
    @Produces(MediaType.APPLICATION_JSON)
    //Pasar información de la URL al codigo
    public Facultad getFacultadById(@PathParam("idFacultad")Integer idFacultad,@PathParam("nombre")String nombre){
        Facultad facultad=new Facultad(idFacultad,nombre); 
        return facultad; 
    }
    
    @Path("registrar")
    @POST
    //Usar URL o el cuerpo del mensaje, trabaja con la ruta y el retorno 
    @Produces (MediaType.APPLICATION_JSON)
    //Los parametros tienen que adaptarse el cliente al nombre asignado
    public Facultad registrarFacultad(@FormParam("nombre") String nombre){
    
        Facultad facultad=new Facultad(0,nombre);
        Mensaje mensaje=null; 
        SqlSession conn=MyBatisUtil.getSession();
        if(conn!=null){
            try {
                int numFilas=conn.insert("Facultad.registrar",facultad);
                //Se realiza un comit para todas las operacioines con insersion,actualización o eliminación
                conn.commit();
                if(numFilas>0){
                    mensaje=new Mensaje("Facultad guardado con exito",false);
                }else{
                    mensaje=new Mensaje("Lo sentimos la Facultad no se pudo registrar",true);
                }
            } catch (Exception e) {
                mensaje=new Mensaje("Error"+e.getMessage(),true);
            }finally{
                //Sirve para cerrar la conexion el finally 
                conn.close();
            }
        }
       return mensaje;
    }
    
    @Path("actualizar")
    @PUT
    //Usar URL o el cuerpo del mensaje, trabaja con la ruta y el retorno 
    @Produces (MediaType.APPLICATION_JSON)
    //Los parametros tienen que adaptarse el cliente al nombre asignado
    public Facultad actualizarFacultad(@FormParam("idFacultad") Integer idFacultad, 
                                    @FormParam("nombre") String nombre){
        Facultad facultad=new Facultad(idFacultad,nombre);
        Mensaje mensaje=null; 
        SqlSession conn=MyBatisUtil.getSession();
        if(conn!=null){
            try {
                int numFilas=conn.update("Facultad.editarFacultad",facultad);
                //Se realiza un comit para todas las operacioines con insersion,actualización o eliminación
                conn.commit();
                if(numFilas>0){
                    mensaje=new Mensaje("Facultad"+facultad.getNombre()+"actualizar con exito", false); 
                }else{
                    mensaje=new Mensaje("Lo sentimos el usuario no se pudo registrar",true);
                }
            } catch (Exception e) {
                mensaje=new Mensaje("Error"+e.getMessage(),true);
            }finally{
                //Sirve para cerrar la conexion el finally 
                conn.close();
            }
        }
        return mensaje; 
    }    
}
