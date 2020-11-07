package cr.ac.ucr.ecci.eseg.catbi.BaseDatos;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Biblioteca;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.DataBaseHelperRoom;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Reservacion;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Session;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Usuario;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Material;

public class FireBaseDataBaseBiblitecaHelper {
    private FirebaseDatabase database;
    private DatabaseReference referenciaBiblioteca;
    private DatabaseReference referenciaMaterial;
    private DatabaseReference referenciaUsuarios;
    private DatabaseReference referenciaReserva;
    private FirebaseAuth mAuth;
    FirebaseUser user;


    private String[] filtro;
    private List<Biblioteca> listaBibliotecas= new ArrayList<>();
    private List<Material> listaMaterial = new ArrayList<>();
    private List<Usuario> listaUsuarios = new ArrayList<>();
    private Usuario usuario;
    private List<Reservacion> listaReservaciones = new ArrayList<>();
    public List<Material> getListaMaterial() {
        return listaMaterial;
    }
    private Session session;
    private DataBaseHelperRoom dbLocal;

    // Interfaces utilizadas para recuperar todos los datos de firebase
    public interface AllBibliotecasDataStatus {
        void DataIsLoaded(List<Biblioteca> bibliotecas);
    }

    public interface AllMaterialesDataStatus {
        void DataIsLoaded(List<Material> materiales);
    }

    public interface AllUsuariosDataStatus{
        void DataIsLoaded(List<Usuario> usuarios);
    }

    public interface AllReservacionesDataStatus{
        void DataIsLoaded(List<Reservacion> reservaciones);
    }
    //

    public interface DataStatus{
        void dataLoaded(List<Biblioteca>ListaBibliotecas, List<String>keys);
    }
    public interface MaterialDataStatus{
        void DataIsLoaded(List<Material> material, List<String> keys);
    }

    public interface UsuariosDataStatus{
        void DataIsLoaded(Usuario usuario);
    }


    public interface ReservaDataStatus{
        void DataIsLoaded(List<Reservacion> reservacion, List<String> keys);
    }

    public FireBaseDataBaseBiblitecaHelper() {
        database=FirebaseDatabase.getInstance();
        referenciaBiblioteca= database.getReference("Bibliotecas");
        referenciaMaterial= database.getReference("Material");
        //filtro = "";
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        filtro = null;
        referenciaUsuarios= database.getReference("Usuarios");
        referenciaReserva = database.getReference("Usuario_Material");
    }

    public void readBibliotecas(final DataStatus dataStatus){
        referenciaBiblioteca.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaBibliotecas.clear();
                List<String>keys= new ArrayList<>();
                for(DataSnapshot keyNode: dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Biblioteca listaBiblioteca= keyNode.getValue(Biblioteca.class);
                    listaBiblioteca.setBibliotecaID(keyNode.getKey());
                    listaBibliotecas.add(listaBiblioteca);
                }
                dataStatus.dataLoaded(listaBibliotecas,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // Método que recupera todos las bibliotecas de la base de datos de firebase
    public void readAllBibliotecas(final AllBibliotecasDataStatus dataStatus){
        referenciaBiblioteca.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaBibliotecas.clear();
                for(DataSnapshot keyNode: dataSnapshot.getChildren()){
                    Biblioteca listaBiblioteca= keyNode.getValue(Biblioteca.class);
                    listaBiblioteca.setBibliotecaID(keyNode.getKey());
                    listaBibliotecas.add(listaBiblioteca);
                }
                dataStatus.DataIsLoaded(listaBibliotecas);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // Método que recupera todos los materiales de la base de datos de firebase
    public void readAllMateriales(final AllMaterialesDataStatus dataStatus){
        referenciaMaterial.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaMaterial.clear();
                for(DataSnapshot keyNode: dataSnapshot.getChildren()){
                    Material material= keyNode.getValue(Material.class);
                    material.setMaterialID(keyNode.getKey());
                    listaMaterial.add(material);
                }
                dataStatus.DataIsLoaded(listaMaterial);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // Método que recupera todos los usuarios de la base de datos de firebase
    public void readAllUsuarios(final AllUsuariosDataStatus dataStatus){
        referenciaUsuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaUsuarios.clear();
                for(DataSnapshot keyNode: dataSnapshot.getChildren()){
                    Usuario usuario = keyNode.getValue(Usuario.class);
                    listaUsuarios.add(usuario);
                }
                dataStatus.DataIsLoaded(listaUsuarios);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // Método que recupera todas las reservaciones de la base de datos de firebase
    public void readAllReservaciones(final AllReservacionesDataStatus dataStatus){
        referenciaReserva.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaReservaciones.clear();
                for(DataSnapshot keyNode: dataSnapshot.getChildren()){
                    Reservacion reservacion  = keyNode.getValue(Reservacion.class);
                    reservacion.setReservacionID(keyNode.getKey());
                    listaReservaciones.add(reservacion);
                }
                dataStatus.DataIsLoaded(listaReservaciones);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void readMaterial(final MaterialDataStatus materialDataStatus, final String[] filtro){
        this.filtro = filtro;
        referenciaMaterial.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaMaterial.clear();
                List<String> keys = new ArrayList<>();

                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Material material = keyNode.getValue(Material.class);
                    material.setMaterialID(keyNode.getKey());

                    if(filtro[2].equalsIgnoreCase("todas")){
                        realizarFiltradoSinColeccion(material, filtro, listaMaterial);
                    }else{
                        realizarFiltradoConColeccion(material, filtro, listaMaterial);
                    }
                }
                materialDataStatus.DataIsLoaded(listaMaterial,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    public void readUsuarios(final UsuariosDataStatus userDataStatus, final String correoP){
        referenciaUsuarios.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    if(ds.child("correo").getValue().equals(correoP)){
                        String nombreUsuarioFromDB = ds.child("nombre").getValue(String.class);
                        String correoFromDB = ds.child("correo").getValue(String.class);
                        usuario = new Usuario(correoFromDB, nombreUsuarioFromDB, null);
                        userDataStatus.DataIsLoaded(usuario);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void readReservas(final ReservaDataStatus reservaDataStatus, final String correoP){
        referenciaReserva.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaReservaciones.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    if(ds.child("correoUsuario").getValue().equals(correoP)){
                        //String tituloMaterialFromDB = ds.child("tituloMaterial").getValue(String.class);
                        //String fechaLimiteFromDB = ds.child("fechaLimite").getValue(String.class);
                        //Reservacion reserva = new Reservacion(tituloMaterialFromDB,fechaLimiteFromDB);
                        keys.add(ds.getKey());
                        Reservacion reserva = ds.getValue(Reservacion.class);
                        listaReservaciones.add(reserva);
                    }
                }
                reservaDataStatus.DataIsLoaded(listaReservaciones, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void realizarFiltradoConColeccion(Material material, String[] filtro, List<Material> listaMaterial) {
        String titulo = StringUtils.stripAccents(material.getTitulo()).toLowerCase();
        String autor = StringUtils.stripAccents(material.getAutor()).toLowerCase();
        String idioma = StringUtils.stripAccents(material.getIdioma()).toLowerCase();

        String palabraClave = filtro[0];
        String campoBusqueda = filtro[1];

        String colecionFiltro = filtro[2];
        String colecionRecuperada = StringUtils.stripAccents(material.getColeccion()).toLowerCase();

        switch (campoBusqueda){
            case "titulo":
                if(titulo.contains(palabraClave) && colecionFiltro.equalsIgnoreCase(colecionRecuperada)){
                    listaMaterial.add(material);
                }
                break;
            case "autor":
                if(autor.contains(palabraClave) && colecionFiltro.equalsIgnoreCase(colecionRecuperada)){
                    listaMaterial.add(material);
                }
                break;
            case "idioma":
                if(idioma.contains(palabraClave) && colecionFiltro.equalsIgnoreCase(colecionRecuperada)){
                    listaMaterial.add(material);
                }
                break;
            case "todo":
                if((titulo.contains(palabraClave) || autor.contains(palabraClave) || idioma.contains(palabraClave)) && colecionFiltro.equalsIgnoreCase(colecionRecuperada) ){
                    listaMaterial.add(material);
                }
                break;
        }
    }

    private void realizarFiltradoSinColeccion(Material material, String[] filtro, List<Material> listaMaterial) {
        String titulo = StringUtils.stripAccents(material.getTitulo()).toLowerCase();
        String autor = StringUtils.stripAccents(material.getAutor()).toLowerCase();
        String idioma = StringUtils.stripAccents(material.getIdioma()).toLowerCase();

        String palabraClave = filtro[0];
        String campoBusqueda = filtro[1];

        switch (campoBusqueda){
            case "titulo":
                if(titulo.contains(palabraClave)){
                    listaMaterial.add(material);
                }
                break;
            case "autor":
                if(autor.contains(palabraClave)){
                    listaMaterial.add(material);
                }
                break;
            case "idioma":
                if(idioma.contains(palabraClave)){
                    listaMaterial.add(material);
                }
                break;
            case "todo":
                if(titulo.contains(palabraClave) || autor.contains(palabraClave) || idioma.contains(palabraClave)){
                    listaMaterial.add(material);
                }
                break;
        }
    }



    public boolean addReserva(Reservacion r, String c , Context context){
        actualizaCantMaterial(c,r.getMaterialID());
        DateFormat df = new SimpleDateFormat("yyMMddHHmmssZ");
        String date = df.format(Calendar.getInstance().getTime());
        session = new Session(context);
        String correoUsuario = session.getCorreo();
        try{
            r.setCorreoUsuario(correoUsuario);
        }catch (Exception e){
            r.setCorreoUsuario("DESCONOCIDO");
        }

        int dias= Integer.parseInt(r.getFechaLimite());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,dias);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String fechaLimite = sdf.format(calendar.getTime());
        r.setFechaLimite(fechaLimite);
        boolean resultado;
        try{
            referenciaReserva.child(String.valueOf(date)).setValue(r);
            r.setReservacionID(date);
            DataBaseHelperRoom dbLocal = new DataBaseHelperRoom(context);
            dbLocal.insertarReservacion(r);
            resultado=true;
        }catch (Exception e){
            resultado=false;
        }
        return resultado;

    }

    private void actualizaCantMaterial(String  cant, String id){
        int c=Integer.parseInt(cant)-1;
        referenciaMaterial.child(id).child("cantidad").setValue(String.valueOf(c));
    }

    // funcion que agrega un nuevo valor a la base de datos y genera una id unica
    public boolean addMaterial( Material m, Context context){
        boolean add=true;
        int inicio=0;
        int fin=2;
        DateFormat df = new SimpleDateFormat("yyMMddHHmmssZ");
        String date = df.format(Calendar.getInstance().getTime());
        String titulo=m.getTitulo();
        String year=m.getAño();
        String biblio=m.getBiblioteca();
        String idM=year+date+biblio.substring(inicio,fin)+titulo.substring(inicio,1);
        m.setMaterialID(idM);
        dbLocal = new DataBaseHelperRoom(context);
        try{
            referenciaMaterial.child(idM).setValue(m);
            dbLocal.insertarMaterial(m);

        }catch (Exception e){
            add=false;
        }
        return add;
    }

    public void actualizarDatos(Material material){
        referenciaMaterial.child(material.getMaterialID()).child("autor").setValue(material.getAutor());
        referenciaMaterial.child(material.getMaterialID()).child("biblioteca").setValue(material.getBiblioteca());
        referenciaMaterial.child(material.getMaterialID()).child("coleccion").setValue(material.getColeccion());
        referenciaMaterial.child(material.getMaterialID()).child("cantidad").setValue(material.getCantidad());
        referenciaMaterial.child(material.getMaterialID()).child("formato").setValue(material.getFormato());
        referenciaMaterial.child(material.getMaterialID()).child("idioma").setValue(material.getIdioma());
        referenciaMaterial.child(material.getMaterialID()).child("titulo").setValue(material.getTitulo());
    }

    public boolean eliminarMaterial(String id) {
        try{
            referenciaMaterial.child(id).removeValue();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
