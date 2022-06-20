/*
 * MALPICA SOFT
 * VERSION 1.0
 * MARCOS VITUREIRA
 */
package malpica;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import stock.*;
import compras.*;
import ventas.*;
import gastos.*;
import clientes.*;
import proveedores.*;

/**
 *
 * @author Marcos Vitureira
 */
public class Usuario extends javax.swing.JFrame {

    // Variables del mouse
    int xMouse;
    int yMouse;

    // Variables generales
    public String datoFechaOculta;
    public String datoNombre;
    public String datoDireccion;
    public String datoLocalidad;
    public String datoNroCuit;
    public String datoCondicion;
    public String datoEmail;
    public String datoTelefono;
    public String datoEstado;
    public String datoFechaAlta;
    public String datoUsuario;
    public String datoUsuario1;
    public String datoMesVenta;
    public String datoVentasTotal;
    public String datoMesCompra;
    public String datoComprasTotal;
    public String datoMesGasto;
    public String datoGastosTotal;
    public String datoBalanceTotal;
    public double datoVentasTotal1;
    public double datoComprasTotal1;
    public double datoGastosTotal1;
    public double datoBalanceTotal1;
    public int ID;

    public Usuario() {
        // Propiedades del frame
        initComponents();
        setLayout(null);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/images/logos/logo.png")).getImage());

        // Métodos a ejecutar
        ocultarPaneles();
        fecha();
        datos();
        totales();
    }

    // Método para ocultar los paneles del menú principal, de ayuda y el mensaje de error
    public void ocultarPaneles() {
        panelStock.setVisible(false);
        panelCompras.setVisible(false);
        panelVentas.setVisible(false);
        panelGastos.setVisible(false);
        panelClientes.setVisible(false);
        panelProveedores.setVisible(false);
        panelNombreInfo.setVisible(false);
        panelDireccionInfo.setVisible(false);
        panelLocalidadInfo.setVisible(false);
        panelNroCuitInfo.setVisible(false);
        panelCondicionInfo.setVisible(false);
        panelEmailInfo.setVisible(false);
        panelTelefonoInfo.setVisible(false);

        botonVolver.requestFocus();
    }

    // Método para obtener la fecha actual
    public void fecha() {
        Date sistFecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YY");
        campoFechaOculta.setText(formato.format(sistFecha));

        datoFechaOculta = campoFechaOculta.getText().trim();
        campoFechaOculta.setVisible(false);
    }

    // Método para consultar y obtener los datos del usuario
    public void datos() {
        
        Login datodeusuario = new Login();
        datodeusuario.setDatoUsuario(datoUsuario1);
        datoUsuario1 = datodeusuario.getDatoUsuario();

        try {
            // Establece conexión con DB para efectuar la consulta
            Connection con0 = Conexion.conectar();
            PreparedStatement sta0 = con0.prepareStatement("select * from base where usuario = '" + datoUsuario1 + "'");
            ResultSet res0 = sta0.executeQuery();

            if (res0.next()) {
                // Si se encuentra, obtiene el resto de los datos
                ID = res0.getInt("id");
                campoNombre.setText(res0.getString("comercio"));
                campoDireccion.setText(res0.getString("direccion"));
                campoLocalidad.setText(res0.getString("localidad"));
                campoNroCuit.setText(res0.getString("cuit"));
                campoCondicion.setText(res0.getString("condicion"));
                campoEmail.setText(res0.getString("email"));
                campoTelefono.setText(res0.getString("telefono"));
                datoEstado = res0.getString("estado");
                datoFechaAlta = res0.getString("fecha_alta");

                labelEstado1.setText(datoEstado);
                labelFechaAlta1.setText(datoFechaAlta);

                botonLimpiar.requestFocus();

                con0.close();

            } else {

                campoNombre.setRequestFocusEnabled(true);
                campoDireccion.setRequestFocusEnabled(true);
                campoLocalidad.setRequestFocusEnabled(true);
                campoNroCuit.setRequestFocusEnabled(true);
                campoCondicion.setRequestFocusEnabled(true);
                campoEmail.setRequestFocusEnabled(true);
                campoTelefono.setRequestFocusEnabled(true);

                campoNombre.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
                campoDireccion.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
                campoLocalidad.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
                campoNroCuit.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
                campoCondicion.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
                campoEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
                campoTelefono.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

                campoNombre.requestFocus();

                con0.close();
            }

            datodeusuario.dispose();

        } catch (SQLException e) {
            // Si no se conecta con la DB, muestra un mensaje de error
            JOptionPane.showConfirmDialog(null, "Error de conexión: Base usuarios!", "Información", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Método para obtener los totales del mes por usuario
    public void totales() {

        datoMesVenta = datoFechaOculta.charAt(3) + "" + datoFechaOculta.charAt(4);
        datoMesCompra = datoFechaOculta.charAt(3) + "" + datoFechaOculta.charAt(4);
        datoMesGasto = datoFechaOculta.charAt(3) + "" + datoFechaOculta.charAt(4);

        try {
            // Establece conexión con DB para efectuar la consulta
            Connection con = Conexion.conectar();
            PreparedStatement sta = con.prepareStatement("select sum(precio_total) as total_ventas, mes_factura from ventas "
                    + "where mes_factura = '" + datoMesVenta + "'");

            ResultSet res = sta.executeQuery();

            if (res.next()) {
                // Si se encuentra, obtiene el resto de los datos
                datoVentasTotal1 = res.getDouble("total_ventas");

                datoVentasTotal = "$ " + String.format("%.2f", datoVentasTotal1).replace('.', ',');

                labelVentasMes1.setText(datoVentasTotal);

                con.close();

            } else {
                // Si no se encuentra, completa con ceros
                labelVentasMes1.setText("$ 0,00");
                con.close();
            }

        } catch (SQLException e) {
            // Si no se conecta con la DB, muestra un mensaje de error
            JOptionPane.showConfirmDialog(null, "Error de conexión: Base ventas!", "Información", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            // Establece conexión con DB para efectuar la consulta
            Connection con1 = Conexion.conectar();
            PreparedStatement sta1 = con1.prepareStatement("select sum(precio_total) as total_compras, mes_factura from compras "
                    + "where mes_factura = '" + datoMesCompra + "'");

            ResultSet res1 = sta1.executeQuery();

            if (res1.next()) {
                // Si se encuentra, obtiene el resto de los datos
                datoComprasTotal1 = res1.getDouble("total_compras");

                datoComprasTotal = "$ " + String.format("%.2f", datoComprasTotal1).replace('.', ',');

                labelComprasMes1.setText(datoComprasTotal);

                con1.close();

            } else {
                // Si no se encuentra, completa con ceros
                labelComprasMes1.setText("$ 0,00");
                con1.close();
            }

        } catch (SQLException e) {
            // Si no se conecta con la DB, muestra un mensaje de error
            JOptionPane.showConfirmDialog(null, "Error de conexión: Base compras!", "Información", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            // Establece conexión con DB para efectuar la consulta
            Connection con2 = Conexion.conectar();
            PreparedStatement sta2 = con2.prepareStatement("select sum(precio_total) as total_gastos, mes_factura from gastos "
                    + "where mes_factura = '" + datoMesGasto + "'");

            ResultSet res2 = sta2.executeQuery();

            if (res2.next()) {
                // Si se encuentra, obtiene el resto de los datos
                datoGastosTotal1 = res2.getDouble("total_gastos");

                datoGastosTotal = "$ " + String.format("%.2f", datoGastosTotal1).replace('.', ',');

                labelGastosMes1.setText(datoGastosTotal);

                con2.close();

            } else {
                // Si no se encuentra, completa con ceros
                labelGastosMes1.setText("$ 0,00");
                con2.close();
            }

        } catch (SQLException e) {
            // Si no se conecta con la DB, muestra un mensaje de error
            JOptionPane.showConfirmDialog(null, "Error de conexión: Base gastos!", "Información", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }

        // Luego de obtener los totales, establece el total final en el balance
        datoBalanceTotal1 = datoVentasTotal1 - datoComprasTotal1 - datoGastosTotal1;
        datoBalanceTotal = "$ " + String.format("%.2f", datoBalanceTotal1).replace('.', ',');
        labelBalanceMes1.setText(datoBalanceTotal);

        if (datoBalanceTotal1 > 0) {

            labelBalanceMes1.setForeground(new java.awt.Color(0, 255, 0));

        } else {

            labelBalanceMes1.setForeground(new java.awt.Color(255, 0, 0));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelIngresar = new javax.swing.JPanel();
        panelMenu = new javax.swing.JPanel();
        labelUsuario = new javax.swing.JLabel();
        labelStock = new javax.swing.JLabel();
        labelCompras = new javax.swing.JLabel();
        labelVentas = new javax.swing.JLabel();
        labelGastos = new javax.swing.JLabel();
        labelClientes = new javax.swing.JLabel();
        labelProveedores = new javax.swing.JLabel();
        labelReportes = new javax.swing.JLabel();
        labelAyuda = new javax.swing.JLabel();
        labelMinimizar = new javax.swing.JLabel();
        labelCerrar = new javax.swing.JLabel();
        fondoMenu = new javax.swing.JLabel();
        panelStock = new javax.swing.JPanel();
        labelIngresarProd = new javax.swing.JLabel();
        labelConsultarProd = new javax.swing.JLabel();
        labelModificarProd = new javax.swing.JLabel();
        labelEliminarProd = new javax.swing.JLabel();
        panelCompras = new javax.swing.JPanel();
        labelIngresarComp = new javax.swing.JLabel();
        labelConsultarComp = new javax.swing.JLabel();
        labelModificarComp = new javax.swing.JLabel();
        labelEliminarComp = new javax.swing.JLabel();
        panelVentas = new javax.swing.JPanel();
        labelIngresarVent = new javax.swing.JLabel();
        labelConsultarVent = new javax.swing.JLabel();
        labelModificarVent = new javax.swing.JLabel();
        labelEliminarVent = new javax.swing.JLabel();
        panelGastos = new javax.swing.JPanel();
        labelIngresarGast = new javax.swing.JLabel();
        labelConsultarGast = new javax.swing.JLabel();
        labelModificarGast = new javax.swing.JLabel();
        labelEliminarGast = new javax.swing.JLabel();
        panelClientes = new javax.swing.JPanel();
        labelIngresarClie = new javax.swing.JLabel();
        labelConsultarClie = new javax.swing.JLabel();
        labelModificarClie = new javax.swing.JLabel();
        labelEliminarClie = new javax.swing.JLabel();
        panelProveedores = new javax.swing.JPanel();
        labelIngresarProv = new javax.swing.JLabel();
        labelConsultarProv = new javax.swing.JLabel();
        labelModificarProv = new javax.swing.JLabel();
        labelEliminarProv = new javax.swing.JLabel();
        panelFondoIngresar = new javax.swing.JPanel();
        panelNombreInfo = new javax.swing.JPanel();
        labelNombreInfo = new javax.swing.JLabel();
        panelDireccionInfo = new javax.swing.JPanel();
        labelDireccionInfo = new javax.swing.JLabel();
        panelLocalidadInfo = new javax.swing.JPanel();
        labelLocalidadInfo = new javax.swing.JLabel();
        panelNroCuitInfo = new javax.swing.JPanel();
        labelNroCuitInfo = new javax.swing.JLabel();
        panelCondicionInfo = new javax.swing.JPanel();
        labelCondicionInfo2 = new javax.swing.JLabel();
        labelCondicionInfo3 = new javax.swing.JLabel();
        panelEmailInfo = new javax.swing.JPanel();
        labelEmailInfo = new javax.swing.JLabel();
        panelTelefonoInfo = new javax.swing.JPanel();
        labelTelefonoInfo = new javax.swing.JLabel();
        labelUsuario1 = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        labelDireccion = new javax.swing.JLabel();
        labelLocalidad = new javax.swing.JLabel();
        labelNroCuit = new javax.swing.JLabel();
        labelCondicion = new javax.swing.JLabel();
        labelEmail = new javax.swing.JLabel();
        labelTelefono = new javax.swing.JLabel();
        campoNombre = new javax.swing.JTextField();
        campoDireccion = new javax.swing.JTextField();
        campoLocalidad = new javax.swing.JTextField();
        campoNroCuit = new javax.swing.JTextField();
        campoCondicion = new javax.swing.JTextField();
        campoEmail = new javax.swing.JTextField();
        campoTelefono = new javax.swing.JTextField();
        fondoNombre = new javax.swing.JLabel();
        fondoDireccion = new javax.swing.JLabel();
        fondoLocalidad = new javax.swing.JLabel();
        fondoNroCuit = new javax.swing.JLabel();
        fondoCondicion = new javax.swing.JLabel();
        fondoEmail = new javax.swing.JLabel();
        fondoTelefono = new javax.swing.JLabel();
        labelEstado = new javax.swing.JLabel();
        labelEstado1 = new javax.swing.JLabel();
        labelFechaAlta = new javax.swing.JLabel();
        labelFechaAlta1 = new javax.swing.JLabel();
        labelBalanceMes = new javax.swing.JLabel();
        labelBalanceMes1 = new javax.swing.JLabel();
        labelVentasMes = new javax.swing.JLabel();
        labelVentasMes1 = new javax.swing.JLabel();
        labelComprasMes = new javax.swing.JLabel();
        labelComprasMes1 = new javax.swing.JLabel();
        labelGastosMes = new javax.swing.JLabel();
        labelGastosMes1 = new javax.swing.JLabel();
        labelMensaje = new javax.swing.JLabel();
        botonVolver = new javax.swing.JButton();
        botonLimpiar = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        botonGuardar = new javax.swing.JButton();
        fondoPanelIngresar1 = new javax.swing.JLabel();
        fondoPanelIngresar2 = new javax.swing.JLabel();
        fondoIngresarCompra = new javax.swing.JLabel();
        campoFechaOculta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MS - Usuario");
        setUndecorated(true);

        panelIngresar.setPreferredSize(new java.awt.Dimension(800, 500));
        panelIngresar.setLayout(null);

        panelMenu.setLayout(null);

        labelUsuario.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        labelUsuario.setForeground(new java.awt.Color(255, 180, 140));
        labelUsuario.setText("USUARIO");
        labelUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelUsuarioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelUsuarioMouseExited(evt);
            }
        });
        panelMenu.add(labelUsuario);
        labelUsuario.setBounds(10, 0, 56, 30);

        labelStock.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        labelStock.setForeground(new java.awt.Color(255, 180, 140));
        labelStock.setText("STOCK");
        labelStock.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelStockMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelStockMouseExited(evt);
            }
        });
        panelMenu.add(labelStock);
        labelStock.setBounds(76, 0, 40, 30);

        labelCompras.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        labelCompras.setForeground(new java.awt.Color(255, 180, 140));
        labelCompras.setText("COMPRAS");
        labelCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelComprasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelComprasMouseExited(evt);
            }
        });
        panelMenu.add(labelCompras);
        labelCompras.setBounds(126, 0, 62, 30);

        labelVentas.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        labelVentas.setForeground(new java.awt.Color(255, 180, 140));
        labelVentas.setText("VENTAS");
        labelVentas.setToolTipText("");
        labelVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelVentasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelVentasMouseExited(evt);
            }
        });
        panelMenu.add(labelVentas);
        labelVentas.setBounds(198, 0, 49, 30);

        labelGastos.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        labelGastos.setForeground(new java.awt.Color(255, 180, 140));
        labelGastos.setText("GASTOS");
        labelGastos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelGastos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelGastosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelGastosMouseExited(evt);
            }
        });
        panelMenu.add(labelGastos);
        labelGastos.setBounds(257, 0, 49, 30);

        labelClientes.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        labelClientes.setForeground(new java.awt.Color(255, 180, 140));
        labelClientes.setText("CLIENTES");
        labelClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelClientesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelClientesMouseExited(evt);
            }
        });
        panelMenu.add(labelClientes);
        labelClientes.setBounds(316, 0, 56, 30);

        labelProveedores.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        labelProveedores.setForeground(new java.awt.Color(255, 180, 140));
        labelProveedores.setText("PROVEEDORES");
        labelProveedores.setToolTipText("");
        labelProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelProveedoresMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelProveedoresMouseExited(evt);
            }
        });
        panelMenu.add(labelProveedores);
        labelProveedores.setBounds(382, 0, 90, 30);

        labelReportes.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        labelReportes.setForeground(new java.awt.Color(255, 180, 140));
        labelReportes.setText("REPORTES");
        labelReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelReportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelReportesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelReportesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelReportesMouseExited(evt);
            }
        });
        panelMenu.add(labelReportes);
        labelReportes.setBounds(482, 0, 65, 30);

        labelAyuda.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        labelAyuda.setForeground(new java.awt.Color(255, 180, 140));
        labelAyuda.setText("AYUDA");
        labelAyuda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelAyuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelAyudaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelAyudaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelAyudaMouseExited(evt);
            }
        });
        panelMenu.add(labelAyuda);
        labelAyuda.setBounds(557, 0, 43, 30);

        labelMinimizar.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labelMinimizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMinimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/minimizar.png"))); // NOI18N
        labelMinimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelMinimizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelMinimizarMouseExited(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMinimizarMouseClicked(evt);
            }
        });
        panelMenu.add(labelMinimizar);
        labelMinimizar.setBounds(735, 0, 30, 30);

        labelCerrar.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labelCerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/cerrar.png"))); // NOI18N
        labelCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelCerrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelCerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelCerrarMouseExited(evt);
            }
        });
        panelMenu.add(labelCerrar);
        labelCerrar.setBounds(765, 0, 30, 30);

        fondoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/varios/barramenu.png"))); // NOI18N
        fondoMenu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                fondoMenuMouseDragged(evt);
            }
        });
        fondoMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                fondoMenuMousePressed(evt);
            }
        });
        panelMenu.add(fondoMenu);
        fondoMenu.setBounds(0, 0, 800, 30);

        panelIngresar.add(panelMenu);
        panelMenu.setBounds(0, 0, 800, 30);

        panelStock.setBackground(new java.awt.Color(20, 20, 20));
        panelStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelStockMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelStockMouseExited(evt);
            }
        });
        panelStock.setLayout(null);

        labelIngresarProd.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelIngresarProd.setForeground(new java.awt.Color(175, 175, 175));
        labelIngresarProd.setText("Ingresar productos");
        labelIngresarProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelIngresarProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelIngresarProdMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelIngresarProdMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelIngresarProdMouseExited(evt);
            }
        });
        panelStock.add(labelIngresarProd);
        labelIngresarProd.setBounds(10, 10, 120, 17);

        labelConsultarProd.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelConsultarProd.setForeground(new java.awt.Color(175, 175, 175));
        labelConsultarProd.setText("Consultar productos");
        labelConsultarProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelConsultarProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelConsultarProdMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelConsultarProdMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelConsultarProdMouseExited(evt);
            }
        });
        panelStock.add(labelConsultarProd);
        labelConsultarProd.setBounds(10, 40, 120, 17);

        labelModificarProd.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelModificarProd.setForeground(new java.awt.Color(175, 175, 175));
        labelModificarProd.setText("Modificar productos");
        labelModificarProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelModificarProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelModificarProdMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelModificarProdMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelModificarProdMouseExited(evt);
            }
        });
        panelStock.add(labelModificarProd);
        labelModificarProd.setBounds(10, 70, 120, 17);

        labelEliminarProd.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelEliminarProd.setForeground(new java.awt.Color(175, 175, 175));
        labelEliminarProd.setText("Eliminar productos");
        labelEliminarProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelEliminarProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelEliminarProdMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelEliminarProdMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelEliminarProdMouseExited(evt);
            }
        });
        panelStock.add(labelEliminarProd);
        labelEliminarProd.setBounds(10, 100, 120, 17);

        panelIngresar.add(panelStock);
        panelStock.setBounds(76, 30, 140, 130);

        panelCompras.setBackground(new java.awt.Color(20, 20, 20));
        panelCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelComprasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelComprasMouseExited(evt);
            }
        });
        panelCompras.setLayout(null);

        labelIngresarComp.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelIngresarComp.setForeground(new java.awt.Color(175, 175, 175));
        labelIngresarComp.setText("Ingresar compras");
        labelIngresarComp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelIngresarComp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelIngresarCompMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelIngresarCompMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelIngresarCompMouseExited(evt);
            }
        });
        panelCompras.add(labelIngresarComp);
        labelIngresarComp.setBounds(10, 10, 110, 17);

        labelConsultarComp.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelConsultarComp.setForeground(new java.awt.Color(175, 175, 175));
        labelConsultarComp.setText("Consultar compras");
        labelConsultarComp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelConsultarComp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelConsultarCompMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelConsultarCompMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelConsultarCompMouseExited(evt);
            }
        });
        panelCompras.add(labelConsultarComp);
        labelConsultarComp.setBounds(10, 40, 110, 17);

        labelModificarComp.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelModificarComp.setForeground(new java.awt.Color(175, 175, 175));
        labelModificarComp.setText("Modificar compras");
        labelModificarComp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelModificarComp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelModificarCompMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelModificarCompMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelModificarCompMouseExited(evt);
            }
        });
        panelCompras.add(labelModificarComp);
        labelModificarComp.setBounds(10, 70, 110, 17);

        labelEliminarComp.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelEliminarComp.setForeground(new java.awt.Color(175, 175, 175));
        labelEliminarComp.setText("Eliminar compras");
        labelEliminarComp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelEliminarComp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelEliminarCompMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelEliminarCompMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelEliminarCompMouseExited(evt);
            }
        });
        panelCompras.add(labelEliminarComp);
        labelEliminarComp.setBounds(10, 100, 110, 17);

        panelIngresar.add(panelCompras);
        panelCompras.setBounds(126, 30, 130, 130);

        panelVentas.setBackground(new java.awt.Color(20, 20, 20));
        panelVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelVentasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelVentasMouseExited(evt);
            }
        });
        panelVentas.setLayout(null);

        labelIngresarVent.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelIngresarVent.setForeground(new java.awt.Color(175, 175, 175));
        labelIngresarVent.setText("Ingresar ventas");
        labelIngresarVent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelIngresarVent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelIngresarVentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelIngresarVentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelIngresarVentMouseExited(evt);
            }
        });
        panelVentas.add(labelIngresarVent);
        labelIngresarVent.setBounds(10, 10, 100, 17);

        labelConsultarVent.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelConsultarVent.setForeground(new java.awt.Color(175, 175, 175));
        labelConsultarVent.setText("Consultar ventas");
        labelConsultarVent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelConsultarVent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelConsultarVentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelConsultarVentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelConsultarVentMouseExited(evt);
            }
        });
        panelVentas.add(labelConsultarVent);
        labelConsultarVent.setBounds(10, 40, 100, 17);

        labelModificarVent.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelModificarVent.setForeground(new java.awt.Color(175, 175, 175));
        labelModificarVent.setText("Modificar ventas");
        labelModificarVent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelModificarVent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelModificarVentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelModificarVentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelModificarVentMouseExited(evt);
            }
        });
        panelVentas.add(labelModificarVent);
        labelModificarVent.setBounds(10, 70, 100, 17);

        labelEliminarVent.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelEliminarVent.setForeground(new java.awt.Color(175, 175, 175));
        labelEliminarVent.setText("Eliminar ventas");
        labelEliminarVent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelEliminarVent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelEliminarVentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelEliminarVentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelEliminarVentMouseExited(evt);
            }
        });
        panelVentas.add(labelEliminarVent);
        labelEliminarVent.setBounds(10, 100, 100, 17);

        panelIngresar.add(panelVentas);
        panelVentas.setBounds(198, 30, 120, 130);

        panelGastos.setBackground(new java.awt.Color(20, 20, 20));
        panelGastos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelGastosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelGastosMouseExited(evt);
            }
        });
        panelGastos.setLayout(null);

        labelIngresarGast.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelIngresarGast.setForeground(new java.awt.Color(175, 175, 175));
        labelIngresarGast.setText("Ingresar gastos");
        labelIngresarGast.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelIngresarGast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelIngresarGastMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelIngresarGastMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelIngresarGastMouseExited(evt);
            }
        });
        panelGastos.add(labelIngresarGast);
        labelIngresarGast.setBounds(10, 10, 100, 17);

        labelConsultarGast.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelConsultarGast.setForeground(new java.awt.Color(175, 175, 175));
        labelConsultarGast.setText("Consultar gastos");
        labelConsultarGast.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelConsultarGast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelConsultarGastMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelConsultarGastMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelConsultarGastMouseExited(evt);
            }
        });
        panelGastos.add(labelConsultarGast);
        labelConsultarGast.setBounds(10, 40, 100, 17);

        labelModificarGast.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelModificarGast.setForeground(new java.awt.Color(175, 175, 175));
        labelModificarGast.setText("Modificar gastos");
        labelModificarGast.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelModificarGast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelModificarGastMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelModificarGastMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelModificarGastMouseExited(evt);
            }
        });
        panelGastos.add(labelModificarGast);
        labelModificarGast.setBounds(10, 70, 100, 17);

        labelEliminarGast.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelEliminarGast.setForeground(new java.awt.Color(175, 175, 175));
        labelEliminarGast.setText("Eliminar gastos");
        labelEliminarGast.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelEliminarGast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelEliminarGastMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelEliminarGastMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelEliminarGastMouseExited(evt);
            }
        });
        panelGastos.add(labelEliminarGast);
        labelEliminarGast.setBounds(10, 100, 100, 17);

        panelIngresar.add(panelGastos);
        panelGastos.setBounds(257, 30, 120, 130);

        panelClientes.setBackground(new java.awt.Color(20, 20, 20));
        panelClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelClientesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelClientesMouseExited(evt);
            }
        });
        panelClientes.setLayout(null);

        labelIngresarClie.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelIngresarClie.setForeground(new java.awt.Color(175, 175, 175));
        labelIngresarClie.setText("Ingresar clientes");
        labelIngresarClie.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelIngresarClie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelIngresarClieMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelIngresarClieMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelIngresarClieMouseExited(evt);
            }
        });
        panelClientes.add(labelIngresarClie);
        labelIngresarClie.setBounds(10, 10, 100, 17);

        labelConsultarClie.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelConsultarClie.setForeground(new java.awt.Color(175, 175, 175));
        labelConsultarClie.setText("Consultar clientes");
        labelConsultarClie.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelConsultarClie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelConsultarClieMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelConsultarClieMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelConsultarClieMouseExited(evt);
            }
        });
        panelClientes.add(labelConsultarClie);
        labelConsultarClie.setBounds(10, 40, 100, 17);

        labelModificarClie.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelModificarClie.setForeground(new java.awt.Color(175, 175, 175));
        labelModificarClie.setText("Modificar clientes");
        labelModificarClie.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelModificarClie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelModificarClieMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelModificarClieMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelModificarClieMouseExited(evt);
            }
        });
        panelClientes.add(labelModificarClie);
        labelModificarClie.setBounds(10, 70, 100, 17);

        labelEliminarClie.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelEliminarClie.setForeground(new java.awt.Color(175, 175, 175));
        labelEliminarClie.setText("Eliminar clientes");
        labelEliminarClie.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelEliminarClie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelEliminarClieMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelEliminarClieMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelEliminarClieMouseExited(evt);
            }
        });
        panelClientes.add(labelEliminarClie);
        labelEliminarClie.setBounds(10, 100, 100, 17);

        panelIngresar.add(panelClientes);
        panelClientes.setBounds(316, 30, 130, 130);

        panelProveedores.setBackground(new java.awt.Color(20, 20, 20));
        panelProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelProveedoresMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelProveedoresMouseExited(evt);
            }
        });
        panelProveedores.setLayout(null);

        labelIngresarProv.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelIngresarProv.setForeground(new java.awt.Color(175, 175, 175));
        labelIngresarProv.setText("Ingresar proveedores");
        labelIngresarProv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelIngresarProv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelIngresarProvMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelIngresarProvMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelIngresarProvMouseExited(evt);
            }
        });
        panelProveedores.add(labelIngresarProv);
        labelIngresarProv.setBounds(10, 10, 130, 17);

        labelConsultarProv.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelConsultarProv.setForeground(new java.awt.Color(175, 175, 175));
        labelConsultarProv.setText("Consultar proveedores");
        labelConsultarProv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelConsultarProv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelConsultarProvMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelConsultarProvMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelConsultarProvMouseExited(evt);
            }
        });
        panelProveedores.add(labelConsultarProv);
        labelConsultarProv.setBounds(10, 40, 130, 17);

        labelModificarProv.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelModificarProv.setForeground(new java.awt.Color(175, 175, 175));
        labelModificarProv.setText("Modificar proveedores");
        labelModificarProv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelModificarProv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelModificarProvMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelModificarProvMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelModificarProvMouseExited(evt);
            }
        });
        panelProveedores.add(labelModificarProv);
        labelModificarProv.setBounds(10, 70, 130, 17);

        labelEliminarProv.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelEliminarProv.setForeground(new java.awt.Color(175, 175, 175));
        labelEliminarProv.setText("Eliminar proveedores");
        labelEliminarProv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelEliminarProv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelEliminarProvMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelEliminarProvMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelEliminarProvMouseExited(evt);
            }
        });
        panelProveedores.add(labelEliminarProv);
        labelEliminarProv.setBounds(10, 100, 130, 17);

        panelIngresar.add(panelProveedores);
        panelProveedores.setBounds(382, 30, 150, 130);

        panelFondoIngresar.setMinimumSize(new java.awt.Dimension(740, 370));
        panelFondoIngresar.setLayout(null);

        panelNombreInfo.setBackground(new java.awt.Color(0, 0, 0, 230));
        panelNombreInfo.setLayout(null);

        labelNombreInfo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelNombreInfo.setForeground(new java.awt.Color(255, 180, 140));
        labelNombreInfo.setText("Hasta 50 caracteres!");
        panelNombreInfo.add(labelNombreInfo);
        labelNombreInfo.setBounds(10, 5, 120, 17);

        panelFondoIngresar.add(panelNombreInfo);
        panelNombreInfo.setBounds(0, 40, 140, 25);

        panelDireccionInfo.setBackground(new java.awt.Color(0, 0, 0, 230));
        panelDireccionInfo.setLayout(null);

        labelDireccionInfo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelDireccionInfo.setForeground(new java.awt.Color(255, 180, 140));
        labelDireccionInfo.setText("Hasta 50 caracteres!");
        panelDireccionInfo.add(labelDireccionInfo);
        labelDireccionInfo.setBounds(10, 5, 120, 17);

        panelFondoIngresar.add(panelDireccionInfo);
        panelDireccionInfo.setBounds(0, 110, 140, 25);

        panelLocalidadInfo.setBackground(new java.awt.Color(0, 0, 0, 230));
        panelLocalidadInfo.setLayout(null);

        labelLocalidadInfo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelLocalidadInfo.setForeground(new java.awt.Color(255, 180, 140));
        labelLocalidadInfo.setText("Hasta 50 caracteres!");
        panelLocalidadInfo.add(labelLocalidadInfo);
        labelLocalidadInfo.setBounds(10, 5, 120, 17);

        panelFondoIngresar.add(panelLocalidadInfo);
        panelLocalidadInfo.setBounds(0, 185, 140, 25);

        panelNroCuitInfo.setBackground(new java.awt.Color(0, 0, 0, 230));
        panelNroCuitInfo.setLayout(null);

        labelNroCuitInfo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelNroCuitInfo.setForeground(new java.awt.Color(255, 180, 140));
        labelNroCuitInfo.setText("Formato: \"20-00000000-0\"");
        panelNroCuitInfo.add(labelNroCuitInfo);
        labelNroCuitInfo.setBounds(10, 5, 152, 17);

        panelFondoIngresar.add(panelNroCuitInfo);
        panelNroCuitInfo.setBounds(160, 40, 170, 25);

        panelCondicionInfo.setBackground(new java.awt.Color(0, 0, 0, 230));
        panelCondicionInfo.setLayout(null);

        labelCondicionInfo2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelCondicionInfo2.setForeground(new java.awt.Color(255, 180, 140));
        labelCondicionInfo2.setText("\"MT: Monotributo\"");
        labelCondicionInfo2.setToolTipText("");
        panelCondicionInfo.add(labelCondicionInfo2);
        labelCondicionInfo2.setBounds(10, 5, 110, 17);

        labelCondicionInfo3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelCondicionInfo3.setForeground(new java.awt.Color(255, 180, 140));
        labelCondicionInfo3.setText("\"RI: IVA Inscripto\"");
        panelCondicionInfo.add(labelCondicionInfo3);
        labelCondicionInfo3.setBounds(15, 25, 103, 17);

        panelFondoIngresar.add(panelCondicionInfo);
        panelCondicionInfo.setBounds(300, 15, 130, 50);

        panelEmailInfo.setBackground(new java.awt.Color(0, 0, 0, 230));
        panelEmailInfo.setLayout(null);

        labelEmailInfo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelEmailInfo.setForeground(new java.awt.Color(255, 180, 140));
        labelEmailInfo.setText("Hasta 50 caracteres!");
        panelEmailInfo.add(labelEmailInfo);
        labelEmailInfo.setBounds(10, 5, 120, 17);

        panelFondoIngresar.add(panelEmailInfo);
        panelEmailInfo.setBounds(165, 110, 140, 25);

        panelTelefonoInfo.setBackground(new java.awt.Color(0, 0, 0, 230));
        panelTelefonoInfo.setLayout(null);

        labelTelefonoInfo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelTelefonoInfo.setForeground(new java.awt.Color(255, 180, 140));
        labelTelefonoInfo.setText("Hasta 20 caracteres!");
        panelTelefonoInfo.add(labelTelefonoInfo);
        labelTelefonoInfo.setBounds(10, 5, 120, 17);

        panelFondoIngresar.add(panelTelefonoInfo);
        panelTelefonoInfo.setBounds(175, 185, 140, 25);

        labelUsuario1.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        labelUsuario1.setForeground(new java.awt.Color(255, 90, 0));
        labelUsuario1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelUsuario1.setText("PERFIL DEL USUARIO");
        labelUsuario1.setToolTipText("");
        panelFondoIngresar.add(labelUsuario1);
        labelUsuario1.setBounds(0, 15, 740, 40);

        labelNombre.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelNombre.setForeground(new java.awt.Color(175, 175, 175));
        labelNombre.setText("Nombre del comercio");
        labelNombre.setToolTipText("");
        labelNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelNombreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelNombreMouseExited(evt);
            }
        });
        panelFondoIngresar.add(labelNombre);
        labelNombre.setBounds(10, 65, 160, 23);

        labelDireccion.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelDireccion.setForeground(new java.awt.Color(175, 175, 175));
        labelDireccion.setText("Dirección");
        labelDireccion.setToolTipText("");
        labelDireccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelDireccionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelDireccionMouseExited(evt);
            }
        });
        panelFondoIngresar.add(labelDireccion);
        labelDireccion.setBounds(10, 135, 70, 23);

        labelLocalidad.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelLocalidad.setForeground(new java.awt.Color(175, 175, 175));
        labelLocalidad.setText("Localidad");
        labelLocalidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelLocalidadMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelLocalidadMouseExited(evt);
            }
        });
        panelFondoIngresar.add(labelLocalidad);
        labelLocalidad.setBounds(10, 210, 70, 23);

        labelNroCuit.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelNroCuit.setForeground(new java.awt.Color(175, 175, 175));
        labelNroCuit.setText("Nro. CUIT");
        labelNroCuit.setName(""); // NOI18N
        labelNroCuit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelNroCuitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelNroCuitMouseExited(evt);
            }
        });
        panelFondoIngresar.add(labelNroCuit);
        labelNroCuit.setBounds(210, 65, 73, 23);

        labelCondicion.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelCondicion.setForeground(new java.awt.Color(175, 175, 175));
        labelCondicion.setText("Cond.");
        labelCondicion.setName(""); // NOI18N
        labelCondicion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelCondicionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelCondicionMouseExited(evt);
            }
        });
        panelFondoIngresar.add(labelCondicion);
        labelCondicion.setBounds(340, 65, 43, 23);

        labelEmail.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelEmail.setForeground(new java.awt.Color(175, 175, 175));
        labelEmail.setText("Email");
        labelEmail.setName(""); // NOI18N
        labelEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelEmailMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelEmailMouseExited(evt);
            }
        });
        panelFondoIngresar.add(labelEmail);
        labelEmail.setBounds(210, 135, 40, 23);

        labelTelefono.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelTelefono.setForeground(new java.awt.Color(175, 175, 175));
        labelTelefono.setText("Teléfono");
        labelTelefono.setName(""); // NOI18N
        labelTelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelTelefonoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelTelefonoMouseExited(evt);
            }
        });
        panelFondoIngresar.add(labelTelefono);
        labelTelefono.setBounds(210, 210, 66, 23);

        campoNombre.setBackground(new java.awt.Color(0, 0, 0));
        campoNombre.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        campoNombre.setForeground(new java.awt.Color(175, 175, 175));
        campoNombre.setBorder(null);
        campoNombre.setCaretColor(new java.awt.Color(175, 175, 175));
        campoNombre.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        campoNombre.setRequestFocusEnabled(false);
        campoNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoNombreFocusLost(evt);
            }
        });
        campoNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoNombreKeyTyped(evt);
            }
        });
        panelFondoIngresar.add(campoNombre);
        campoNombre.setBounds(15, 90, 170, 30);

        campoDireccion.setBackground(new java.awt.Color(0, 0, 0));
        campoDireccion.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        campoDireccion.setForeground(new java.awt.Color(175, 175, 175));
        campoDireccion.setBorder(null);
        campoDireccion.setCaretColor(new java.awt.Color(175, 175, 175));
        campoDireccion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        campoDireccion.setName(""); // NOI18N
        campoDireccion.setRequestFocusEnabled(false);
        campoDireccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoDireccionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoDireccionFocusLost(evt);
            }
        });
        campoDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoDireccionKeyTyped(evt);
            }
        });
        panelFondoIngresar.add(campoDireccion);
        campoDireccion.setBounds(15, 160, 170, 30);

        campoLocalidad.setBackground(new java.awt.Color(0, 0, 0));
        campoLocalidad.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        campoLocalidad.setForeground(new java.awt.Color(175, 175, 175));
        campoLocalidad.setBorder(null);
        campoLocalidad.setCaretColor(new java.awt.Color(175, 175, 175));
        campoLocalidad.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        campoLocalidad.setRequestFocusEnabled(false);
        campoLocalidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoLocalidadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoLocalidadFocusLost(evt);
            }
        });
        campoLocalidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoLocalidadKeyTyped(evt);
            }
        });
        panelFondoIngresar.add(campoLocalidad);
        campoLocalidad.setBounds(15, 235, 170, 30);

        campoNroCuit.setBackground(new java.awt.Color(0, 0, 0));
        campoNroCuit.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        campoNroCuit.setForeground(new java.awt.Color(175, 175, 175));
        campoNroCuit.setBorder(null);
        campoNroCuit.setCaretColor(new java.awt.Color(175, 175, 175));
        campoNroCuit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        campoNroCuit.setRequestFocusEnabled(false);
        campoNroCuit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoNroCuitFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoNroCuitFocusLost(evt);
            }
        });
        campoNroCuit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoNroCuitKeyTyped(evt);
            }
        });
        panelFondoIngresar.add(campoNroCuit);
        campoNroCuit.setBounds(215, 90, 115, 30);

        campoCondicion.setBackground(new java.awt.Color(0, 0, 0));
        campoCondicion.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        campoCondicion.setForeground(new java.awt.Color(175, 175, 175));
        campoCondicion.setBorder(null);
        campoCondicion.setCaretColor(new java.awt.Color(175, 175, 175));
        campoCondicion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        campoCondicion.setName(""); // NOI18N
        campoCondicion.setRequestFocusEnabled(false);
        campoCondicion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoCondicionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoCondicionFocusLost(evt);
            }
        });
        campoCondicion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoCondicionKeyTyped(evt);
            }
        });
        panelFondoIngresar.add(campoCondicion);
        campoCondicion.setBounds(345, 90, 40, 30);

        campoEmail.setBackground(new java.awt.Color(0, 0, 0));
        campoEmail.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        campoEmail.setForeground(new java.awt.Color(175, 175, 175));
        campoEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        campoEmail.setBorder(null);
        campoEmail.setCaretColor(new java.awt.Color(175, 175, 175));
        campoEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        campoEmail.setName(""); // NOI18N
        campoEmail.setRequestFocusEnabled(false);
        campoEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoEmailFocusLost(evt);
            }
        });
        campoEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoEmailKeyTyped(evt);
            }
        });
        panelFondoIngresar.add(campoEmail);
        campoEmail.setBounds(215, 160, 170, 30);

        campoTelefono.setBackground(new java.awt.Color(0, 0, 0));
        campoTelefono.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        campoTelefono.setForeground(new java.awt.Color(175, 175, 175));
        campoTelefono.setBorder(null);
        campoTelefono.setCaretColor(new java.awt.Color(175, 175, 175));
        campoTelefono.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        campoTelefono.setRequestFocusEnabled(false);
        campoTelefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoTelefonoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoTelefonoFocusLost(evt);
            }
        });
        campoTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoTelefonoKeyTyped(evt);
            }
        });
        panelFondoIngresar.add(campoTelefono);
        campoTelefono.setBounds(215, 235, 170, 30);

        fondoNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-usuarios1.png"))); // NOI18N
        panelFondoIngresar.add(fondoNombre);
        fondoNombre.setBounds(10, 85, 180, 40);

        fondoDireccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-usuarios1.png"))); // NOI18N
        fondoDireccion.setName(""); // NOI18N
        panelFondoIngresar.add(fondoDireccion);
        fondoDireccion.setBounds(10, 155, 180, 40);

        fondoLocalidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-usuarios1.png"))); // NOI18N
        panelFondoIngresar.add(fondoLocalidad);
        fondoLocalidad.setBounds(10, 230, 180, 40);

        fondoNroCuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-nrocuit1.png"))); // NOI18N
        panelFondoIngresar.add(fondoNroCuit);
        fondoNroCuit.setBounds(210, 85, 125, 40);

        fondoCondicion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-condicion1.png"))); // NOI18N
        panelFondoIngresar.add(fondoCondicion);
        fondoCondicion.setBounds(340, 85, 50, 40);

        fondoEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-usuarios1.png"))); // NOI18N
        fondoEmail.setName(""); // NOI18N
        panelFondoIngresar.add(fondoEmail);
        fondoEmail.setBounds(210, 155, 180, 40);

        fondoTelefono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-usuarios1.png"))); // NOI18N
        panelFondoIngresar.add(fondoTelefono);
        fondoTelefono.setBounds(210, 230, 180, 40);

        labelEstado.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelEstado.setForeground(new java.awt.Color(175, 175, 175));
        labelEstado.setText("Estado");
        panelFondoIngresar.add(labelEstado);
        labelEstado.setBounds(450, 80, 50, 23);

        labelEstado1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelEstado1.setForeground(new java.awt.Color(255, 90, 0));
        labelEstado1.setText("-");
        panelFondoIngresar.add(labelEstado1);
        labelEstado1.setBounds(450, 110, 50, 16);

        labelFechaAlta.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelFechaAlta.setForeground(new java.awt.Color(175, 175, 175));
        labelFechaAlta.setText("Fecha de alta");
        labelFechaAlta.setName(""); // NOI18N
        panelFondoIngresar.add(labelFechaAlta);
        labelFechaAlta.setBounds(450, 140, 97, 23);

        labelFechaAlta1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelFechaAlta1.setForeground(new java.awt.Color(255, 90, 0));
        labelFechaAlta1.setText("-");
        panelFondoIngresar.add(labelFechaAlta1);
        labelFechaAlta1.setBounds(450, 170, 70, 23);

        labelBalanceMes.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelBalanceMes.setForeground(new java.awt.Color(175, 175, 175));
        labelBalanceMes.setText("Balance del mes");
        labelBalanceMes.setToolTipText("");
        labelBalanceMes.setName(""); // NOI18N
        panelFondoIngresar.add(labelBalanceMes);
        labelBalanceMes.setBounds(450, 200, 118, 23);

        labelBalanceMes1.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        labelBalanceMes1.setForeground(new java.awt.Color(102, 102, 102));
        labelBalanceMes1.setText("$ 0,00");
        panelFondoIngresar.add(labelBalanceMes1);
        labelBalanceMes1.setBounds(450, 230, 140, 30);

        labelVentasMes.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelVentasMes.setForeground(new java.awt.Color(175, 175, 175));
        labelVentasMes.setText("Ventas del mes");
        labelVentasMes.setName(""); // NOI18N
        panelFondoIngresar.add(labelVentasMes);
        labelVentasMes.setBounds(600, 80, 110, 23);

        labelVentasMes1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelVentasMes1.setForeground(new java.awt.Color(0, 255, 0));
        labelVentasMes1.setText("$ 0,00");
        panelFondoIngresar.add(labelVentasMes1);
        labelVentasMes1.setBounds(600, 110, 130, 23);

        labelComprasMes.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelComprasMes.setForeground(new java.awt.Color(175, 175, 175));
        labelComprasMes.setText("Compras del mes");
        labelComprasMes.setName(""); // NOI18N
        panelFondoIngresar.add(labelComprasMes);
        labelComprasMes.setBounds(600, 140, 125, 23);

        labelComprasMes1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelComprasMes1.setForeground(new java.awt.Color(255, 0, 0));
        labelComprasMes1.setText("$ 0,00");
        panelFondoIngresar.add(labelComprasMes1);
        labelComprasMes1.setBounds(600, 170, 130, 23);

        labelGastosMes.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelGastosMes.setForeground(new java.awt.Color(175, 175, 175));
        labelGastosMes.setText("Gastos del mes");
        labelGastosMes.setName(""); // NOI18N
        panelFondoIngresar.add(labelGastosMes);
        labelGastosMes.setBounds(600, 200, 110, 23);

        labelGastosMes1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelGastosMes1.setForeground(new java.awt.Color(255, 0, 0));
        labelGastosMes1.setText("$ 0,00");
        panelFondoIngresar.add(labelGastosMes1);
        labelGastosMes1.setBounds(600, 230, 130, 23);

        labelMensaje.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelMensaje.setForeground(new java.awt.Color(255, 90, 0));
        labelMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensaje.setToolTipText("");
        panelFondoIngresar.add(labelMensaje);
        labelMensaje.setBounds(0, 275, 740, 30);

        botonVolver.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        botonVolver.setForeground(new java.awt.Color(255, 180, 140));
        botonVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/botones/boton1.png"))); // NOI18N
        botonVolver.setText("VOLVER");
        botonVolver.setBorder(null);
        botonVolver.setBorderPainted(false);
        botonVolver.setContentAreaFilled(false);
        botonVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonVolver.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonVolver.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/botones/boton2.png"))); // NOI18N
        botonVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonVolverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonVolverMouseExited(evt);
            }
        });
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });
        panelFondoIngresar.add(botonVolver);
        botonVolver.setBounds(135, 315, 130, 35);

        botonLimpiar.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        botonLimpiar.setForeground(new java.awt.Color(255, 180, 140));
        botonLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/botones/boton1.png"))); // NOI18N
        botonLimpiar.setText("LIMPIAR");
        botonLimpiar.setBorder(null);
        botonLimpiar.setBorderPainted(false);
        botonLimpiar.setContentAreaFilled(false);
        botonLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLimpiar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/botones/boton2.png"))); // NOI18N
        botonLimpiar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                botonLimpiarFocusGained(evt);
            }
        });
        botonLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonLimpiarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonLimpiarMouseExited(evt);
            }
        });
        botonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarActionPerformed(evt);
            }
        });
        panelFondoIngresar.add(botonLimpiar);
        botonLimpiar.setBounds(305, 315, 130, 35);

        botonModificar.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        botonModificar.setForeground(new java.awt.Color(255, 180, 140));
        botonModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/botones/boton1.png"))); // NOI18N
        botonModificar.setText("MODIFICAR");
        botonModificar.setBorder(null);
        botonModificar.setBorderPainted(false);
        botonModificar.setContentAreaFilled(false);
        botonModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonModificar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/botones/boton2.png"))); // NOI18N
        botonModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonModificarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonModificarMouseExited(evt);
            }
        });
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });
        panelFondoIngresar.add(botonModificar);
        botonModificar.setBounds(475, 315, 130, 35);

        botonGuardar.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        botonGuardar.setForeground(new java.awt.Color(255, 180, 140));
        botonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/botones/boton1.png"))); // NOI18N
        botonGuardar.setText("GUARDAR");
        botonGuardar.setBorder(null);
        botonGuardar.setBorderPainted(false);
        botonGuardar.setContentAreaFilled(false);
        botonGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonGuardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/botones/boton2.png"))); // NOI18N
        botonGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonGuardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonGuardarMouseExited(evt);
            }
        });
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });
        panelFondoIngresar.add(botonGuardar);
        botonGuardar.setBounds(475, 315, 130, 35);

        fondoPanelIngresar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/varios/cuadro2.png"))); // NOI18N
        fondoPanelIngresar1.setToolTipText("");
        fondoPanelIngresar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fondoPanelIngresar1MouseClicked(evt);
            }
        });
        panelFondoIngresar.add(fondoPanelIngresar1);
        fondoPanelIngresar1.setBounds(0, 0, 740, 370);

        fondoPanelIngresar2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        fondoPanelIngresar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondos/fondo2.png"))); // NOI18N
        fondoPanelIngresar2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        panelFondoIngresar.add(fondoPanelIngresar2);
        fondoPanelIngresar2.setBounds(-30, -80, 800, 500);

        panelIngresar.add(panelFondoIngresar);
        panelFondoIngresar.setBounds(30, 80, 740, 370);

        fondoIngresarCompra.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        fondoIngresarCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondos/fondo2.png"))); // NOI18N
        fondoIngresarCompra.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        fondoIngresarCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fondoIngresarCompraMouseClicked(evt);
            }
        });
        panelIngresar.add(fondoIngresarCompra);
        fondoIngresarCompra.setBounds(0, 0, 800, 500);
        panelIngresar.add(campoFechaOculta);
        campoFechaOculta.setBounds(10, 470, 90, 24);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonVolverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonVolverMouseEntered
        botonVolver.setForeground(new java.awt.Color(255, 90, 0));
    }//GEN-LAST:event_botonVolverMouseEntered

    private void botonVolverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonVolverMouseExited
        botonVolver.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_botonVolverMouseExited

    private void botonModificarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonModificarMouseEntered
        botonModificar.setForeground(new java.awt.Color(255, 90, 0));
    }//GEN-LAST:event_botonModificarMouseEntered

    private void botonModificarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonModificarMouseExited
        botonModificar.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_botonModificarMouseExited

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonVolverActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        campoNombre.setRequestFocusEnabled(true);
        campoDireccion.setRequestFocusEnabled(true);
        campoLocalidad.setRequestFocusEnabled(true);
        campoNroCuit.setRequestFocusEnabled(true);
        campoCondicion.setRequestFocusEnabled(true);
        campoEmail.setRequestFocusEnabled(true);
        campoTelefono.setRequestFocusEnabled(true);

        campoNombre.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoDireccion.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoLocalidad.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoNroCuit.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoCondicion.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoTelefono.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        campoNombre.requestFocus();

        botonModificar.setVisible(false);
        botonGuardar.setVisible(true);

        labelMensaje.setVisible(false);
    }//GEN-LAST:event_botonModificarActionPerformed

    private void fondoIngresarCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fondoIngresarCompraMouseClicked
        // Oculta paneles que no queden visibles clickeando fuera de ellos y recupera el color de los labels
        panelStock.setVisible(false);
        panelCompras.setVisible(false);
        panelVentas.setVisible(false);
        panelGastos.setVisible(false);
        panelClientes.setVisible(false);
        panelProveedores.setVisible(false);
        labelUsuario.setForeground(new java.awt.Color(255, 180, 140));
        labelStock.setForeground(new java.awt.Color(255, 180, 140));
        labelCompras.setForeground(new java.awt.Color(255, 180, 140));
        labelVentas.setForeground(new java.awt.Color(255, 180, 140));
        labelGastos.setForeground(new java.awt.Color(255, 180, 140));
        labelClientes.setForeground(new java.awt.Color(255, 180, 140));
        labelProveedores.setForeground(new java.awt.Color(255, 180, 140));
        labelReportes.setForeground(new java.awt.Color(255, 180, 140));
        labelAyuda.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_fondoIngresarCompraMouseClicked

    private void fondoPanelIngresar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fondoPanelIngresar1MouseClicked
        // Oculta paneles que no queden visibles clickeando fuera de ellos y recupera el color de los labels
        panelStock.setVisible(false);
        panelCompras.setVisible(false);
        panelVentas.setVisible(false);
        panelGastos.setVisible(false);
        panelClientes.setVisible(false);
        panelProveedores.setVisible(false);
        labelUsuario.setForeground(new java.awt.Color(255, 180, 140));
        labelStock.setForeground(new java.awt.Color(255, 180, 140));
        labelCompras.setForeground(new java.awt.Color(255, 180, 140));
        labelVentas.setForeground(new java.awt.Color(255, 180, 140));
        labelGastos.setForeground(new java.awt.Color(255, 180, 140));
        labelClientes.setForeground(new java.awt.Color(255, 180, 140));
        labelProveedores.setForeground(new java.awt.Color(255, 180, 140));
        labelReportes.setForeground(new java.awt.Color(255, 180, 140));
        labelAyuda.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_fondoPanelIngresar1MouseClicked

    private void botonGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonGuardarMouseEntered
        botonGuardar.setForeground(new java.awt.Color(255, 90, 0));
    }//GEN-LAST:event_botonGuardarMouseEntered

    private void botonGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonGuardarMouseExited
        botonGuardar.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_botonGuardarMouseExited

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        // Obtengo los datos de los campos de texto
        datoNombre = campoNombre.getText();
        datoDireccion = campoDireccion.getText();
        datoLocalidad = campoLocalidad.getText();
        datoNroCuit = campoNroCuit.getText().trim();
        datoCondicion = campoCondicion.getText().trim();
        datoEmail = campoEmail.getText().trim();
        datoTelefono = campoTelefono.getText();

        // Continúa si el número de factura contiene todos los dígitos
        if (!datoNombre.equals("") && !datoDireccion.equals("") && !datoLocalidad.equals("") && datoNroCuit.length() == 13
                && datoCondicion.length() == 2 && datoEmail.contains("@") && datoEmail.contains(".") && !datoTelefono.equals("")) {

            try {
                // Establece conexión con DB para consultar la factura ingresada
                Connection con = Conexion.conectar();
                PreparedStatement sta = con.prepareStatement("select * from base where id = '" + ID + "'");
                ResultSet res = sta.executeQuery();

                if (res.next()) {
                    // Si la factura ya fue creada, obtiene el resto de los datos
                    Connection con1 = Conexion.conectar();
                    PreparedStatement sta1 = con1.prepareStatement("update base set comercio=?, direccion=?, localidad=?, "
                            + "cuit=?, condicion=?, email=?, telefono=? where id = '" + ID + "'");

                    sta1.setString(1, datoNombre);
                    sta1.setString(2, datoDireccion);
                    sta1.setString(3, datoLocalidad);
                    sta1.setString(4, datoNroCuit);
                    sta1.setString(5, datoCondicion);
                    sta1.setString(6, datoEmail);
                    sta1.setString(7, datoTelefono);

                    sta1.executeUpdate();
                    con1.close();
                    con.close();

                    // Muestra un mensaje de guardado, reestablece campos y cursor
                    labelMensaje.setForeground(new java.awt.Color(0, 255, 0));
                    labelMensaje.setText("Los datos fueron modificados exitosamente!");
                    labelMensaje.setVisible(true);

                    campoNombre.setRequestFocusEnabled(false);
                    campoDireccion.setRequestFocusEnabled(false);
                    campoLocalidad.setRequestFocusEnabled(false);
                    campoNroCuit.setRequestFocusEnabled(false);
                    campoCondicion.setRequestFocusEnabled(false);
                    campoEmail.setRequestFocusEnabled(false);
                    campoTelefono.setRequestFocusEnabled(false);

                    campoNombre.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    campoDireccion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    campoLocalidad.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    campoNroCuit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    campoCondicion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    campoEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    campoTelefono.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                    botonModificar.setVisible(true);
                    botonGuardar.setVisible(false);

                    botonLimpiar.requestFocus();
                }

            } catch (SQLException e) {
                // Si no se conecta con la DB, muestra un mensaje de error
                JOptionPane.showConfirmDialog(null, "Error de conexión: Base usuarios!", "Información", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            // Muestra un mensaje de error si la factura ingresada no existe en la DB 
            labelMensaje.setForeground(new java.awt.Color(255, 0, 0));
            labelMensaje.setText("Por favor, revise los datos ingresados!");
            labelMensaje.setVisible(true);
        }
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void campoNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoNombreFocusGained
        fondoNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-usuarios2.png")));
        labelNombre.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_campoNombreFocusGained

    private void campoNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoNombreFocusLost
        fondoNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-usuarios1.png")));
        labelNombre.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_campoNombreFocusLost

    private void campoLocalidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoLocalidadFocusGained
        fondoLocalidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-usuarios2.png")));
        labelLocalidad.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_campoLocalidadFocusGained

    private void campoLocalidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoLocalidadFocusLost
        fondoLocalidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-usuarios1.png")));
        labelLocalidad.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_campoLocalidadFocusLost

    private void campoTelefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoTelefonoFocusGained
        fondoTelefono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-usuarios2.png")));
        labelTelefono.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_campoTelefonoFocusGained

    private void campoTelefonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoTelefonoFocusLost
        fondoTelefono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-usuarios1.png")));
        labelTelefono.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_campoTelefonoFocusLost

    private void campoNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNombreKeyTyped
        // Restricción y limitación de caracteres
        char c = evt.getKeyChar();

        if (Character.isLowerCase(c)) {

            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }

        if (campoNombre.getText().length() >= 50) {

            evt.consume();
        }
    }//GEN-LAST:event_campoNombreKeyTyped

    private void campoLocalidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoLocalidadKeyTyped
        // Restricción y limitación de caracteres
        char c = evt.getKeyChar();

        if (Character.isLowerCase(c)) {

            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }

        if (campoLocalidad.getText().length() >= 50) {

            evt.consume();
        }
    }//GEN-LAST:event_campoLocalidadKeyTyped

    private void campoTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoTelefonoKeyTyped
        // Restricción y limitación de caracteres
        char c = evt.getKeyChar();

        if ((c < '0' || c > '9') && !(c == 32) && !(c == 40) && !(c == 41) && !(c == 43) && !(c == 45)) {

            evt.consume();
        }

        if (campoTelefono.getText().length() >= 20) {

            evt.consume();
        }
    }//GEN-LAST:event_campoTelefonoKeyTyped

    private void labelNombreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNombreMouseEntered
        panelNombreInfo.setVisible(true);
    }//GEN-LAST:event_labelNombreMouseEntered

    private void labelNombreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNombreMouseExited
        panelNombreInfo.setVisible(false);
    }//GEN-LAST:event_labelNombreMouseExited

    private void labelLocalidadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelLocalidadMouseEntered
        panelLocalidadInfo.setVisible(true);
    }//GEN-LAST:event_labelLocalidadMouseEntered

    private void labelLocalidadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelLocalidadMouseExited
        panelLocalidadInfo.setVisible(false);
    }//GEN-LAST:event_labelLocalidadMouseExited

    private void labelDireccionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDireccionMouseEntered
        panelDireccionInfo.setVisible(true);
    }//GEN-LAST:event_labelDireccionMouseEntered

    private void labelDireccionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDireccionMouseExited
        panelDireccionInfo.setVisible(false);
    }//GEN-LAST:event_labelDireccionMouseExited

    private void campoEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoEmailFocusGained
        fondoEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-usuarios2.png")));
        labelEmail.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_campoEmailFocusGained

    private void campoEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoEmailFocusLost
        fondoEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-usuarios1.png")));
        labelEmail.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_campoEmailFocusLost

    private void campoEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoEmailKeyTyped
        // Restricción y limitación de caracteres
        if (campoEmail.getText().length() >= 50) {

            evt.consume();
        }
    }//GEN-LAST:event_campoEmailKeyTyped

    private void labelNroCuitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNroCuitMouseEntered
        panelNroCuitInfo.setVisible(true);
    }//GEN-LAST:event_labelNroCuitMouseEntered

    private void labelNroCuitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNroCuitMouseExited
        panelNroCuitInfo.setVisible(false);
    }//GEN-LAST:event_labelNroCuitMouseExited

    private void campoDireccionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDireccionFocusGained
        fondoDireccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-usuarios2.png")));
        labelDireccion.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_campoDireccionFocusGained

    private void campoDireccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDireccionFocusLost
        fondoDireccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-usuarios1.png")));
        labelDireccion.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_campoDireccionFocusLost

    private void campoDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDireccionKeyTyped
        // Restricción y limitación de caracteres
        char c = evt.getKeyChar();

        if (Character.isLowerCase(c)) {

            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }

        if (campoDireccion.getText().length() >= 50) {

            evt.consume();
        }
    }//GEN-LAST:event_campoDireccionKeyTyped

    private void labelTelefonoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTelefonoMouseEntered
        panelTelefonoInfo.setVisible(true);
    }//GEN-LAST:event_labelTelefonoMouseEntered

    private void labelTelefonoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTelefonoMouseExited
        panelTelefonoInfo.setVisible(false);
    }//GEN-LAST:event_labelTelefonoMouseExited

    private void labelEmailMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEmailMouseEntered
        panelEmailInfo.setVisible(true);
    }//GEN-LAST:event_labelEmailMouseEntered

    private void labelEmailMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEmailMouseExited
        panelEmailInfo.setVisible(false);
    }//GEN-LAST:event_labelEmailMouseExited

    private void campoCondicionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoCondicionFocusGained
        fondoCondicion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-condicion2.png")));
        labelCondicion.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_campoCondicionFocusGained

    private void campoCondicionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoCondicionFocusLost
        fondoCondicion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-condicion1.png")));
        labelCondicion.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_campoCondicionFocusLost

    private void campoCondicionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCondicionKeyTyped
        // Restricción y limitación de caracteres
        char c = evt.getKeyChar();

        if (Character.isLowerCase(c)) {

            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }

        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')) {

            evt.consume();
        }

        if (campoCondicion.getText().length() >= 2) {

            evt.consume();
        }
    }//GEN-LAST:event_campoCondicionKeyTyped

    private void labelUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelUsuarioMouseClicked
        new Usuario().setVisible(true);
    }//GEN-LAST:event_labelUsuarioMouseClicked

    private void labelUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelUsuarioMouseEntered
        labelUsuario.setForeground(new java.awt.Color(255, 90, 0));
        labelStock.setForeground(new java.awt.Color(255, 180, 140));
        labelCompras.setForeground(new java.awt.Color(255, 180, 140));
        labelVentas.setForeground(new java.awt.Color(255, 180, 140));
        labelGastos.setForeground(new java.awt.Color(255, 180, 140));
        labelClientes.setForeground(new java.awt.Color(255, 180, 140));
        labelProveedores.setForeground(new java.awt.Color(255, 180, 140));
        labelReportes.setForeground(new java.awt.Color(255, 180, 140));
        labelAyuda.setForeground(new java.awt.Color(255, 180, 140));
        panelStock.setVisible(false);
        panelCompras.setVisible(false);
        panelVentas.setVisible(false);
        panelGastos.setVisible(false);
        panelClientes.setVisible(false);
        panelProveedores.setVisible(false);
    }//GEN-LAST:event_labelUsuarioMouseEntered

    private void labelUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelUsuarioMouseExited
        labelUsuario.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelUsuarioMouseExited

    private void labelStockMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelStockMouseEntered
        labelStock.setForeground(new java.awt.Color(255, 90, 0));
        labelUsuario.setForeground(new java.awt.Color(255, 180, 140));
        labelCompras.setForeground(new java.awt.Color(255, 180, 140));
        labelVentas.setForeground(new java.awt.Color(255, 180, 140));
        labelGastos.setForeground(new java.awt.Color(255, 180, 140));
        labelClientes.setForeground(new java.awt.Color(255, 180, 140));
        labelProveedores.setForeground(new java.awt.Color(255, 180, 140));
        labelReportes.setForeground(new java.awt.Color(255, 180, 140));
        labelAyuda.setForeground(new java.awt.Color(255, 180, 140));
        panelStock.setVisible(true);
        panelCompras.setVisible(false);
        panelVentas.setVisible(false);
        panelGastos.setVisible(false);
        panelClientes.setVisible(false);
        panelProveedores.setVisible(false);
    }//GEN-LAST:event_labelStockMouseEntered

    private void labelStockMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelStockMouseExited
        labelStock.setForeground(new java.awt.Color(255, 180, 140));
        panelStock.setVisible(false);
    }//GEN-LAST:event_labelStockMouseExited

    private void labelComprasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelComprasMouseEntered
        labelCompras.setForeground(new java.awt.Color(255, 90, 0));
        labelStock.setForeground(new java.awt.Color(255, 180, 140));
        labelUsuario.setForeground(new java.awt.Color(255, 180, 140));
        labelVentas.setForeground(new java.awt.Color(255, 180, 140));
        labelGastos.setForeground(new java.awt.Color(255, 180, 140));
        labelClientes.setForeground(new java.awt.Color(255, 180, 140));
        labelProveedores.setForeground(new java.awt.Color(255, 180, 140));
        labelReportes.setForeground(new java.awt.Color(255, 180, 140));
        labelAyuda.setForeground(new java.awt.Color(255, 180, 140));
        panelCompras.setVisible(true);
        panelStock.setVisible(false);
        panelVentas.setVisible(false);
        panelGastos.setVisible(false);
        panelClientes.setVisible(false);
        panelProveedores.setVisible(false);
    }//GEN-LAST:event_labelComprasMouseEntered

    private void labelComprasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelComprasMouseExited
        labelCompras.setForeground(new java.awt.Color(255, 180, 140));
        panelCompras.setVisible(false);
    }//GEN-LAST:event_labelComprasMouseExited

    private void labelVentasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelVentasMouseEntered
        labelVentas.setForeground(new java.awt.Color(255, 90, 0));
        labelStock.setForeground(new java.awt.Color(255, 180, 140));
        labelCompras.setForeground(new java.awt.Color(255, 180, 140));
        labelUsuario.setForeground(new java.awt.Color(255, 180, 140));
        labelGastos.setForeground(new java.awt.Color(255, 180, 140));
        labelClientes.setForeground(new java.awt.Color(255, 180, 140));
        labelProveedores.setForeground(new java.awt.Color(255, 180, 140));
        labelReportes.setForeground(new java.awt.Color(255, 180, 140));
        labelAyuda.setForeground(new java.awt.Color(255, 180, 140));
        panelVentas.setVisible(true);
        panelStock.setVisible(false);
        panelCompras.setVisible(false);
        panelGastos.setVisible(false);
        panelClientes.setVisible(false);
        panelProveedores.setVisible(false);
    }//GEN-LAST:event_labelVentasMouseEntered

    private void labelVentasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelVentasMouseExited
        labelVentas.setForeground(new java.awt.Color(255, 180, 140));
        panelVentas.setVisible(false);
    }//GEN-LAST:event_labelVentasMouseExited

    private void labelGastosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelGastosMouseEntered
        labelGastos.setForeground(new java.awt.Color(255, 90, 0));
        labelStock.setForeground(new java.awt.Color(255, 180, 140));
        labelCompras.setForeground(new java.awt.Color(255, 180, 140));
        labelVentas.setForeground(new java.awt.Color(255, 180, 140));
        labelUsuario.setForeground(new java.awt.Color(255, 180, 140));
        labelClientes.setForeground(new java.awt.Color(255, 180, 140));
        labelProveedores.setForeground(new java.awt.Color(255, 180, 140));
        labelReportes.setForeground(new java.awt.Color(255, 180, 140));
        labelAyuda.setForeground(new java.awt.Color(255, 180, 140));
        panelGastos.setVisible(true);
        panelStock.setVisible(false);
        panelCompras.setVisible(false);
        panelVentas.setVisible(false);
        panelClientes.setVisible(false);
        panelProveedores.setVisible(false);
    }//GEN-LAST:event_labelGastosMouseEntered

    private void labelGastosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelGastosMouseExited
        labelGastos.setForeground(new java.awt.Color(255, 180, 140));
        panelGastos.setVisible(false);
    }//GEN-LAST:event_labelGastosMouseExited

    private void labelClientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelClientesMouseEntered
        labelClientes.setForeground(new java.awt.Color(255, 90, 0));
        labelStock.setForeground(new java.awt.Color(255, 180, 140));
        labelCompras.setForeground(new java.awt.Color(255, 180, 140));
        labelVentas.setForeground(new java.awt.Color(255, 180, 140));
        labelGastos.setForeground(new java.awt.Color(255, 180, 140));
        labelUsuario.setForeground(new java.awt.Color(255, 180, 140));
        labelProveedores.setForeground(new java.awt.Color(255, 180, 140));
        labelReportes.setForeground(new java.awt.Color(255, 180, 140));
        labelAyuda.setForeground(new java.awt.Color(255, 180, 140));
        panelClientes.setVisible(true);
        panelStock.setVisible(false);
        panelCompras.setVisible(false);
        panelVentas.setVisible(false);
        panelGastos.setVisible(false);
        panelProveedores.setVisible(false);
    }//GEN-LAST:event_labelClientesMouseEntered

    private void labelClientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelClientesMouseExited
        labelClientes.setForeground(new java.awt.Color(255, 180, 140));
        panelClientes.setVisible(false);
    }//GEN-LAST:event_labelClientesMouseExited

    private void labelProveedoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelProveedoresMouseEntered
        labelProveedores.setForeground(new java.awt.Color(255, 90, 0));
        labelStock.setForeground(new java.awt.Color(255, 180, 140));
        labelCompras.setForeground(new java.awt.Color(255, 180, 140));
        labelVentas.setForeground(new java.awt.Color(255, 180, 140));
        labelGastos.setForeground(new java.awt.Color(255, 180, 140));
        labelClientes.setForeground(new java.awt.Color(255, 180, 140));
        labelUsuario.setForeground(new java.awt.Color(255, 180, 140));
        labelReportes.setForeground(new java.awt.Color(255, 180, 140));
        labelAyuda.setForeground(new java.awt.Color(255, 180, 140));
        panelProveedores.setVisible(true);
        panelStock.setVisible(false);
        panelCompras.setVisible(false);
        panelVentas.setVisible(false);
        panelGastos.setVisible(false);
        panelClientes.setVisible(false);
    }//GEN-LAST:event_labelProveedoresMouseEntered

    private void labelProveedoresMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelProveedoresMouseExited
        labelProveedores.setForeground(new java.awt.Color(255, 180, 140));
        panelProveedores.setVisible(false);
    }//GEN-LAST:event_labelProveedoresMouseExited

    private void labelReportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelReportesMouseClicked
        new Reportes().setVisible(true);
    }//GEN-LAST:event_labelReportesMouseClicked

    private void labelReportesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelReportesMouseEntered
        labelReportes.setForeground(new java.awt.Color(255, 90, 0));
        labelStock.setForeground(new java.awt.Color(255, 180, 140));
        labelCompras.setForeground(new java.awt.Color(255, 180, 140));
        labelVentas.setForeground(new java.awt.Color(255, 180, 140));
        labelGastos.setForeground(new java.awt.Color(255, 180, 140));
        labelClientes.setForeground(new java.awt.Color(255, 180, 140));
        labelProveedores.setForeground(new java.awt.Color(255, 180, 140));
        labelUsuario.setForeground(new java.awt.Color(255, 180, 140));
        labelAyuda.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelReportesMouseEntered

    private void labelReportesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelReportesMouseExited
        labelReportes.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelReportesMouseExited

    private void labelAyudaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAyudaMouseClicked
        new Ayuda().setVisible(true);
    }//GEN-LAST:event_labelAyudaMouseClicked

    private void labelAyudaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAyudaMouseEntered
        labelAyuda.setForeground(new java.awt.Color(255, 90, 0));
        labelStock.setForeground(new java.awt.Color(255, 180, 140));
        labelCompras.setForeground(new java.awt.Color(255, 180, 140));
        labelVentas.setForeground(new java.awt.Color(255, 180, 140));
        labelGastos.setForeground(new java.awt.Color(255, 180, 140));
        labelClientes.setForeground(new java.awt.Color(255, 180, 140));
        labelProveedores.setForeground(new java.awt.Color(255, 180, 140));
        labelReportes.setForeground(new java.awt.Color(255, 180, 140));
        labelUsuario.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelAyudaMouseEntered

    private void labelAyudaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAyudaMouseExited
        labelAyuda.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelAyudaMouseExited

    private void labelMinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMinimizarMouseEntered
        labelMinimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/minimizar1.png")));
    }//GEN-LAST:event_labelMinimizarMouseEntered

    private void labelMinimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMinimizarMouseExited
        labelMinimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/minimizar.png")));
    }//GEN-LAST:event_labelMinimizarMouseExited

    private void labelMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMinimizarMouseClicked
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_labelMinimizarMouseClicked

    private void labelCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerrarMouseEntered
        labelCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/cerrar1.png")));
    }//GEN-LAST:event_labelCerrarMouseEntered

    private void labelCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerrarMouseExited
        labelCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/cerrar.png")));
    }//GEN-LAST:event_labelCerrarMouseExited

    private void labelCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerrarMouseClicked
        this.dispose();
    }//GEN-LAST:event_labelCerrarMouseClicked

    private void fondoMenuMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fondoMenuMouseDragged
        // Movimiento del frame
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_fondoMenuMouseDragged

    private void fondoMenuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fondoMenuMousePressed
        // Movimiento del frame
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_fondoMenuMousePressed

    private void labelIngresarProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIngresarProdMouseClicked
        new IngresarStock().setVisible(true);
        panelStock.setVisible(false);
        labelStock.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelIngresarProdMouseClicked

    private void labelIngresarProdMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIngresarProdMouseEntered
        labelIngresarProd.setForeground(new java.awt.Color(255, 90, 0));
        labelStock.setForeground(new java.awt.Color(255, 90, 0));
        panelStock.setVisible(true);
    }//GEN-LAST:event_labelIngresarProdMouseEntered

    private void labelIngresarProdMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIngresarProdMouseExited
        labelIngresarProd.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelIngresarProdMouseExited

    private void labelConsultarProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelConsultarProdMouseClicked
        new ConsultarStock().setVisible(true);
        panelStock.setVisible(false);
        labelStock.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelConsultarProdMouseClicked

    private void labelConsultarProdMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelConsultarProdMouseEntered
        labelConsultarProd.setForeground(new java.awt.Color(255, 90, 0));
        labelStock.setForeground(new java.awt.Color(255, 90, 0));
        panelStock.setVisible(true);
    }//GEN-LAST:event_labelConsultarProdMouseEntered

    private void labelConsultarProdMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelConsultarProdMouseExited
        labelConsultarProd.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelConsultarProdMouseExited

    private void labelModificarProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelModificarProdMouseClicked
        new ModificarStock().setVisible(true);
        panelStock.setVisible(false);
        labelStock.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelModificarProdMouseClicked

    private void labelModificarProdMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelModificarProdMouseEntered
        labelModificarProd.setForeground(new java.awt.Color(255, 90, 0));
        labelStock.setForeground(new java.awt.Color(255, 90, 0));
        panelStock.setVisible(true);
    }//GEN-LAST:event_labelModificarProdMouseEntered

    private void labelModificarProdMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelModificarProdMouseExited
        labelModificarProd.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelModificarProdMouseExited

    private void labelEliminarProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEliminarProdMouseClicked
        new EliminarStock().setVisible(true);
        panelStock.setVisible(false);
        labelStock.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelEliminarProdMouseClicked

    private void labelEliminarProdMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEliminarProdMouseEntered
        labelEliminarProd.setForeground(new java.awt.Color(255, 90, 0));
        labelStock.setForeground(new java.awt.Color(255, 90, 0));
        panelStock.setVisible(true);
    }//GEN-LAST:event_labelEliminarProdMouseEntered

    private void labelEliminarProdMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEliminarProdMouseExited
        labelEliminarProd.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelEliminarProdMouseExited

    private void panelStockMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelStockMouseEntered
        labelStock.setForeground(new java.awt.Color(255, 90, 0));
        panelStock.setVisible(true);
    }//GEN-LAST:event_panelStockMouseEntered

    private void panelStockMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelStockMouseExited
        labelStock.setForeground(new java.awt.Color(255, 180, 140));
        panelStock.setVisible(false);
    }//GEN-LAST:event_panelStockMouseExited

    private void labelIngresarCompMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIngresarCompMouseClicked
        new IngresarCompras().setVisible(true);
        panelCompras.setVisible(false);
        labelCompras.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelIngresarCompMouseClicked

    private void labelIngresarCompMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIngresarCompMouseEntered
        labelIngresarComp.setForeground(new java.awt.Color(255, 90, 0));
        labelCompras.setForeground(new java.awt.Color(255, 90, 0));
        panelCompras.setVisible(true);
    }//GEN-LAST:event_labelIngresarCompMouseEntered

    private void labelIngresarCompMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIngresarCompMouseExited
        labelIngresarComp.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelIngresarCompMouseExited

    private void labelConsultarCompMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelConsultarCompMouseClicked
        new ConsultarCompras().setVisible(true);
        panelCompras.setVisible(false);
        labelCompras.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelConsultarCompMouseClicked

    private void labelConsultarCompMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelConsultarCompMouseEntered
        labelConsultarComp.setForeground(new java.awt.Color(255, 90, 0));
        labelCompras.setForeground(new java.awt.Color(255, 90, 0));
        panelCompras.setVisible(true);
    }//GEN-LAST:event_labelConsultarCompMouseEntered

    private void labelConsultarCompMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelConsultarCompMouseExited
        labelConsultarComp.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelConsultarCompMouseExited

    private void labelModificarCompMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelModificarCompMouseClicked
        new ModificarCompras().setVisible(true);
        panelCompras.setVisible(false);
        labelCompras.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelModificarCompMouseClicked

    private void labelModificarCompMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelModificarCompMouseEntered
        labelModificarComp.setForeground(new java.awt.Color(255, 90, 0));
        labelCompras.setForeground(new java.awt.Color(255, 90, 0));
        panelCompras.setVisible(true);
    }//GEN-LAST:event_labelModificarCompMouseEntered

    private void labelModificarCompMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelModificarCompMouseExited
        labelModificarComp.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelModificarCompMouseExited

    private void labelEliminarCompMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEliminarCompMouseClicked
        new EliminarCompras().setVisible(true);
        panelCompras.setVisible(false);
        labelCompras.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelEliminarCompMouseClicked

    private void labelEliminarCompMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEliminarCompMouseEntered
        labelEliminarComp.setForeground(new java.awt.Color(255, 90, 0));
        labelCompras.setForeground(new java.awt.Color(255, 90, 0));
        panelCompras.setVisible(true);
    }//GEN-LAST:event_labelEliminarCompMouseEntered

    private void labelEliminarCompMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEliminarCompMouseExited
        labelEliminarComp.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelEliminarCompMouseExited

    private void panelComprasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelComprasMouseEntered
        labelCompras.setForeground(new java.awt.Color(255, 90, 0));
        panelCompras.setVisible(true);
    }//GEN-LAST:event_panelComprasMouseEntered

    private void panelComprasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelComprasMouseExited
        labelCompras.setForeground(new java.awt.Color(255, 180, 140));
        panelCompras.setVisible(false);
    }//GEN-LAST:event_panelComprasMouseExited

    private void labelIngresarVentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIngresarVentMouseClicked
        new IngresarVentas().setVisible(true);
        panelVentas.setVisible(false);
        labelVentas.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelIngresarVentMouseClicked

    private void labelIngresarVentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIngresarVentMouseEntered
        labelIngresarVent.setForeground(new java.awt.Color(255, 90, 0));
        labelVentas.setForeground(new java.awt.Color(255, 90, 0));
        panelVentas.setVisible(true);
    }//GEN-LAST:event_labelIngresarVentMouseEntered

    private void labelIngresarVentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIngresarVentMouseExited
        labelIngresarVent.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelIngresarVentMouseExited

    private void labelConsultarVentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelConsultarVentMouseClicked
        new ConsultarVentas().setVisible(true);
        panelVentas.setVisible(false);
        labelVentas.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelConsultarVentMouseClicked

    private void labelConsultarVentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelConsultarVentMouseEntered
        labelConsultarVent.setForeground(new java.awt.Color(255, 90, 0));
        labelVentas.setForeground(new java.awt.Color(255, 90, 0));
        panelVentas.setVisible(true);
    }//GEN-LAST:event_labelConsultarVentMouseEntered

    private void labelConsultarVentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelConsultarVentMouseExited
        labelConsultarVent.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelConsultarVentMouseExited

    private void labelModificarVentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelModificarVentMouseClicked
        new ModificarVentas().setVisible(true);
        panelVentas.setVisible(false);
        labelVentas.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelModificarVentMouseClicked

    private void labelModificarVentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelModificarVentMouseEntered
        labelModificarVent.setForeground(new java.awt.Color(255, 90, 0));
        labelVentas.setForeground(new java.awt.Color(255, 90, 0));
        panelVentas.setVisible(true);
    }//GEN-LAST:event_labelModificarVentMouseEntered

    private void labelModificarVentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelModificarVentMouseExited
        labelModificarVent.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelModificarVentMouseExited

    private void labelEliminarVentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEliminarVentMouseClicked
        new EliminarVentas().setVisible(true);
        panelVentas.setVisible(false);
        labelVentas.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelEliminarVentMouseClicked

    private void labelEliminarVentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEliminarVentMouseEntered
        labelEliminarVent.setForeground(new java.awt.Color(255, 90, 0));
        labelVentas.setForeground(new java.awt.Color(255, 90, 0));
        panelVentas.setVisible(true);
    }//GEN-LAST:event_labelEliminarVentMouseEntered

    private void labelEliminarVentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEliminarVentMouseExited
        labelEliminarVent.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelEliminarVentMouseExited

    private void panelVentasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelVentasMouseEntered
        labelVentas.setForeground(new java.awt.Color(255, 90, 0));
        panelVentas.setVisible(true);
    }//GEN-LAST:event_panelVentasMouseEntered

    private void panelVentasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelVentasMouseExited
        labelVentas.setForeground(new java.awt.Color(255, 180, 140));
        panelVentas.setVisible(false);
    }//GEN-LAST:event_panelVentasMouseExited

    private void labelIngresarGastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIngresarGastMouseClicked
        new IngresarGastos().setVisible(true);
        panelGastos.setVisible(false);
        labelGastos.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelIngresarGastMouseClicked

    private void labelIngresarGastMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIngresarGastMouseEntered
        labelIngresarGast.setForeground(new java.awt.Color(255, 90, 0));
        labelGastos.setForeground(new java.awt.Color(255, 90, 0));
        panelGastos.setVisible(true);
    }//GEN-LAST:event_labelIngresarGastMouseEntered

    private void labelIngresarGastMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIngresarGastMouseExited
        labelIngresarGast.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelIngresarGastMouseExited

    private void labelConsultarGastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelConsultarGastMouseClicked
        new ConsultarGastos().setVisible(true);
        panelGastos.setVisible(false);
        labelGastos.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelConsultarGastMouseClicked

    private void labelConsultarGastMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelConsultarGastMouseEntered
        labelConsultarGast.setForeground(new java.awt.Color(255, 90, 0));
        labelGastos.setForeground(new java.awt.Color(255, 90, 0));
        panelGastos.setVisible(true);
    }//GEN-LAST:event_labelConsultarGastMouseEntered

    private void labelConsultarGastMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelConsultarGastMouseExited
        labelConsultarGast.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelConsultarGastMouseExited

    private void labelModificarGastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelModificarGastMouseClicked
        new ModificarGastos().setVisible(true);
        panelGastos.setVisible(false);
        labelGastos.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelModificarGastMouseClicked

    private void labelModificarGastMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelModificarGastMouseEntered
        labelModificarGast.setForeground(new java.awt.Color(255, 90, 0));
        labelGastos.setForeground(new java.awt.Color(255, 90, 0));
        panelGastos.setVisible(true);
    }//GEN-LAST:event_labelModificarGastMouseEntered

    private void labelModificarGastMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelModificarGastMouseExited
        labelModificarGast.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelModificarGastMouseExited

    private void labelEliminarGastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEliminarGastMouseClicked
        new EliminarGastos().setVisible(true);
        panelGastos.setVisible(false);
        labelGastos.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelEliminarGastMouseClicked

    private void labelEliminarGastMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEliminarGastMouseEntered
        labelEliminarGast.setForeground(new java.awt.Color(255, 90, 0));
        labelGastos.setForeground(new java.awt.Color(255, 90, 0));
        panelGastos.setVisible(true);
    }//GEN-LAST:event_labelEliminarGastMouseEntered

    private void labelEliminarGastMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEliminarGastMouseExited
        labelEliminarGast.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelEliminarGastMouseExited

    private void panelGastosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelGastosMouseEntered
        labelGastos.setForeground(new java.awt.Color(255, 90, 0));
        panelGastos.setVisible(true);
    }//GEN-LAST:event_panelGastosMouseEntered

    private void panelGastosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelGastosMouseExited
        labelGastos.setForeground(new java.awt.Color(255, 180, 140));
        panelGastos.setVisible(false);
    }//GEN-LAST:event_panelGastosMouseExited

    private void labelIngresarClieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIngresarClieMouseClicked
        new IngresarClientes().setVisible(true);
        panelClientes.setVisible(false);
        labelClientes.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelIngresarClieMouseClicked

    private void labelIngresarClieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIngresarClieMouseEntered
        labelIngresarClie.setForeground(new java.awt.Color(255, 90, 0));
        labelClientes.setForeground(new java.awt.Color(255, 90, 0));
        panelClientes.setVisible(true);
    }//GEN-LAST:event_labelIngresarClieMouseEntered

    private void labelIngresarClieMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIngresarClieMouseExited
        labelIngresarClie.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelIngresarClieMouseExited

    private void labelConsultarClieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelConsultarClieMouseClicked
        new ConsultarClientes().setVisible(true);
        panelClientes.setVisible(false);
        labelClientes.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelConsultarClieMouseClicked

    private void labelConsultarClieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelConsultarClieMouseEntered
        labelConsultarClie.setForeground(new java.awt.Color(255, 90, 0));
        labelClientes.setForeground(new java.awt.Color(255, 90, 0));
        panelClientes.setVisible(true);
    }//GEN-LAST:event_labelConsultarClieMouseEntered

    private void labelConsultarClieMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelConsultarClieMouseExited
        labelConsultarClie.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelConsultarClieMouseExited

    private void labelModificarClieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelModificarClieMouseClicked
        new ModificarClientes().setVisible(true);
        panelClientes.setVisible(false);
        labelClientes.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelModificarClieMouseClicked

    private void labelModificarClieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelModificarClieMouseEntered
        labelModificarClie.setForeground(new java.awt.Color(255, 90, 0));
        labelClientes.setForeground(new java.awt.Color(255, 90, 0));
        panelClientes.setVisible(true);
    }//GEN-LAST:event_labelModificarClieMouseEntered

    private void labelModificarClieMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelModificarClieMouseExited
        labelModificarClie.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelModificarClieMouseExited

    private void labelEliminarClieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEliminarClieMouseClicked
        new EliminarClientes().setVisible(true);
        panelClientes.setVisible(false);
        labelClientes.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelEliminarClieMouseClicked

    private void labelEliminarClieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEliminarClieMouseEntered
        labelEliminarClie.setForeground(new java.awt.Color(255, 90, 0));
        labelClientes.setForeground(new java.awt.Color(255, 90, 0));
        panelClientes.setVisible(true);
    }//GEN-LAST:event_labelEliminarClieMouseEntered

    private void labelEliminarClieMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEliminarClieMouseExited
        labelEliminarClie.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelEliminarClieMouseExited

    private void panelClientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelClientesMouseEntered
        labelClientes.setForeground(new java.awt.Color(255, 90, 0));
        panelClientes.setVisible(true);
    }//GEN-LAST:event_panelClientesMouseEntered

    private void panelClientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelClientesMouseExited
        labelClientes.setForeground(new java.awt.Color(255, 180, 140));
        panelClientes.setVisible(false);
    }//GEN-LAST:event_panelClientesMouseExited

    private void labelIngresarProvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIngresarProvMouseClicked
        new IngresarProveedores().setVisible(true);
        panelProveedores.setVisible(false);
        labelProveedores.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelIngresarProvMouseClicked

    private void labelIngresarProvMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIngresarProvMouseEntered
        labelIngresarProv.setForeground(new java.awt.Color(255, 90, 0));
        labelProveedores.setForeground(new java.awt.Color(255, 90, 0));
        panelProveedores.setVisible(true);
    }//GEN-LAST:event_labelIngresarProvMouseEntered

    private void labelIngresarProvMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIngresarProvMouseExited
        labelIngresarProv.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelIngresarProvMouseExited

    private void labelConsultarProvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelConsultarProvMouseClicked
        new ConsultarProveedores().setVisible(true);
        panelProveedores.setVisible(false);
        labelProveedores.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelConsultarProvMouseClicked

    private void labelConsultarProvMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelConsultarProvMouseEntered
        labelConsultarProv.setForeground(new java.awt.Color(255, 90, 0));
        labelProveedores.setForeground(new java.awt.Color(255, 90, 0));
        panelProveedores.setVisible(true);
    }//GEN-LAST:event_labelConsultarProvMouseEntered

    private void labelConsultarProvMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelConsultarProvMouseExited
        labelConsultarProv.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelConsultarProvMouseExited

    private void labelModificarProvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelModificarProvMouseClicked
        new ModificarProveedores().setVisible(true);
        panelProveedores.setVisible(false);
        labelProveedores.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelModificarProvMouseClicked

    private void labelModificarProvMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelModificarProvMouseEntered
        labelModificarProv.setForeground(new java.awt.Color(255, 90, 0));
        labelProveedores.setForeground(new java.awt.Color(255, 90, 0));
        panelProveedores.setVisible(true);
    }//GEN-LAST:event_labelModificarProvMouseEntered

    private void labelModificarProvMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelModificarProvMouseExited
        labelModificarProv.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelModificarProvMouseExited

    private void labelEliminarProvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEliminarProvMouseClicked
        new EliminarProveedores().setVisible(true);
        panelProveedores.setVisible(false);
        labelProveedores.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_labelEliminarProvMouseClicked

    private void labelEliminarProvMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEliminarProvMouseEntered
        labelEliminarProv.setForeground(new java.awt.Color(255, 90, 0));
        labelProveedores.setForeground(new java.awt.Color(255, 90, 0));
        panelProveedores.setVisible(true);
    }//GEN-LAST:event_labelEliminarProvMouseEntered

    private void labelEliminarProvMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEliminarProvMouseExited
        labelEliminarProv.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_labelEliminarProvMouseExited

    private void panelProveedoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelProveedoresMouseEntered
        labelProveedores.setForeground(new java.awt.Color(255, 90, 0));
        panelProveedores.setVisible(true);
    }//GEN-LAST:event_panelProveedoresMouseEntered

    private void panelProveedoresMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelProveedoresMouseExited
        labelProveedores.setForeground(new java.awt.Color(255, 180, 140));
        panelProveedores.setVisible(false);
    }//GEN-LAST:event_panelProveedoresMouseExited

    private void labelCondicionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCondicionMouseEntered
        panelCondicionInfo.setVisible(true);
    }//GEN-LAST:event_labelCondicionMouseEntered

    private void labelCondicionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCondicionMouseExited
        panelCondicionInfo.setVisible(false);
    }//GEN-LAST:event_labelCondicionMouseExited

    private void campoNroCuitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoNroCuitFocusGained
        fondoNroCuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-nrocuit2.png")));
        labelNroCuit.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_campoNroCuitFocusGained

    private void campoNroCuitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoNroCuitFocusLost
        fondoNroCuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-nrocuit1.png")));
        labelNroCuit.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_campoNroCuitFocusLost

    private void campoNroCuitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNroCuitKeyTyped
        // Restricción y limitación de caracteres
        char c = evt.getKeyChar();

        if ((c < '0' || c > '9') && !(c == 45)) {

            evt.consume();
        }

        if (campoNroCuit.getText().length() >= 13) {

            evt.consume();
        }
    }//GEN-LAST:event_campoNroCuitKeyTyped

    private void botonLimpiarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_botonLimpiarFocusGained
        botonModificar.setVisible(true);
        botonGuardar.setVisible(false);
    }//GEN-LAST:event_botonLimpiarFocusGained

    private void botonLimpiarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonLimpiarMouseEntered
        botonLimpiar.setForeground(new java.awt.Color(255, 90, 0));
    }//GEN-LAST:event_botonLimpiarMouseEntered

    private void botonLimpiarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonLimpiarMouseExited
        botonLimpiar.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_botonLimpiarMouseExited

    private void botonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarActionPerformed
        // Limpia los campos, la visibilidad del mensaje de error y recupera el foco
        campoNombre.setText("");
        campoDireccion.setText("");
        campoLocalidad.setText("");
        campoNroCuit.setText("");
        campoCondicion.setText("");
        campoEmail.setText("");
        campoTelefono.setText("");

        campoNombre.setRequestFocusEnabled(true);
        campoDireccion.setRequestFocusEnabled(true);
        campoLocalidad.setRequestFocusEnabled(true);
        campoNroCuit.setRequestFocusEnabled(true);
        campoCondicion.setRequestFocusEnabled(true);
        campoEmail.setRequestFocusEnabled(true);
        campoTelefono.setRequestFocusEnabled(true);

        campoNombre.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoDireccion.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoLocalidad.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoNroCuit.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoCondicion.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoTelefono.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        campoNombre.requestFocus();

        labelMensaje.setVisible(false);
        
        botonModificar.setVisible(false);
        botonGuardar.setVisible(true);
    }//GEN-LAST:event_botonLimpiarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonLimpiar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton botonVolver;
    private javax.swing.JTextField campoCondicion;
    private javax.swing.JTextField campoDireccion;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoFechaOculta;
    private javax.swing.JTextField campoLocalidad;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JTextField campoNroCuit;
    private javax.swing.JTextField campoTelefono;
    private javax.swing.JLabel fondoCondicion;
    private javax.swing.JLabel fondoDireccion;
    private javax.swing.JLabel fondoEmail;
    private javax.swing.JLabel fondoIngresarCompra;
    private javax.swing.JLabel fondoLocalidad;
    private javax.swing.JLabel fondoMenu;
    private javax.swing.JLabel fondoNombre;
    private javax.swing.JLabel fondoNroCuit;
    private javax.swing.JLabel fondoPanelIngresar1;
    private javax.swing.JLabel fondoPanelIngresar2;
    private javax.swing.JLabel fondoTelefono;
    private javax.swing.JLabel labelAyuda;
    private javax.swing.JLabel labelBalanceMes;
    private javax.swing.JLabel labelBalanceMes1;
    private javax.swing.JLabel labelCerrar;
    private javax.swing.JLabel labelClientes;
    private javax.swing.JLabel labelCompras;
    private javax.swing.JLabel labelComprasMes;
    private javax.swing.JLabel labelComprasMes1;
    private javax.swing.JLabel labelCondicion;
    private javax.swing.JLabel labelCondicionInfo2;
    private javax.swing.JLabel labelCondicionInfo3;
    private javax.swing.JLabel labelConsultarClie;
    private javax.swing.JLabel labelConsultarComp;
    private javax.swing.JLabel labelConsultarGast;
    private javax.swing.JLabel labelConsultarProd;
    private javax.swing.JLabel labelConsultarProv;
    private javax.swing.JLabel labelConsultarVent;
    private javax.swing.JLabel labelDireccion;
    private javax.swing.JLabel labelDireccionInfo;
    private javax.swing.JLabel labelEliminarClie;
    private javax.swing.JLabel labelEliminarComp;
    private javax.swing.JLabel labelEliminarGast;
    private javax.swing.JLabel labelEliminarProd;
    private javax.swing.JLabel labelEliminarProv;
    private javax.swing.JLabel labelEliminarVent;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelEmailInfo;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelEstado1;
    private javax.swing.JLabel labelFechaAlta;
    private javax.swing.JLabel labelFechaAlta1;
    private javax.swing.JLabel labelGastos;
    private javax.swing.JLabel labelGastosMes;
    private javax.swing.JLabel labelGastosMes1;
    private javax.swing.JLabel labelIngresarClie;
    private javax.swing.JLabel labelIngresarComp;
    private javax.swing.JLabel labelIngresarGast;
    private javax.swing.JLabel labelIngresarProd;
    private javax.swing.JLabel labelIngresarProv;
    private javax.swing.JLabel labelIngresarVent;
    private javax.swing.JLabel labelLocalidad;
    private javax.swing.JLabel labelLocalidadInfo;
    private javax.swing.JLabel labelMensaje;
    private javax.swing.JLabel labelMinimizar;
    private javax.swing.JLabel labelModificarClie;
    private javax.swing.JLabel labelModificarComp;
    private javax.swing.JLabel labelModificarGast;
    private javax.swing.JLabel labelModificarProd;
    private javax.swing.JLabel labelModificarProv;
    private javax.swing.JLabel labelModificarVent;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelNombreInfo;
    private javax.swing.JLabel labelNroCuit;
    private javax.swing.JLabel labelNroCuitInfo;
    private javax.swing.JLabel labelProveedores;
    private javax.swing.JLabel labelReportes;
    private javax.swing.JLabel labelStock;
    private javax.swing.JLabel labelTelefono;
    private javax.swing.JLabel labelTelefonoInfo;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JLabel labelUsuario1;
    private javax.swing.JLabel labelVentas;
    private javax.swing.JLabel labelVentasMes;
    private javax.swing.JLabel labelVentasMes1;
    private javax.swing.JPanel panelClientes;
    private javax.swing.JPanel panelCompras;
    private javax.swing.JPanel panelCondicionInfo;
    private javax.swing.JPanel panelDireccionInfo;
    private javax.swing.JPanel panelEmailInfo;
    private javax.swing.JPanel panelFondoIngresar;
    private javax.swing.JPanel panelGastos;
    private javax.swing.JPanel panelIngresar;
    private javax.swing.JPanel panelLocalidadInfo;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelNombreInfo;
    private javax.swing.JPanel panelNroCuitInfo;
    private javax.swing.JPanel panelProveedores;
    private javax.swing.JPanel panelStock;
    private javax.swing.JPanel panelTelefonoInfo;
    private javax.swing.JPanel panelVentas;
    // End of variables declaration//GEN-END:variables
}
