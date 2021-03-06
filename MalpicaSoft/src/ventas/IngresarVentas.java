/*
 * MALPICA SOFT
 * VERSION 1.0
 * MARCOS VITUREIRA
 */
package ventas;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import java.io.*;
import malpica.*;
import stock.*;
import compras.*;
import gastos.*;
import clientes.*;
import java.util.Calendar;
import proveedores.*;

/**
 *
 * @author Marcos Vitureira
 */
public class IngresarVentas extends javax.swing.JFrame {

    // Variables del mouse
    int xMouse;
    int yMouse;

    // Variables generales
    public String datoFechaVenta;
    public String datoNroFactura;
    public String datoLetraComprob;
    public String datoPuntoVenta;
    public String datoNroComprob;
    public String datoCodigoCliente;
    public String datoNombreCliente;
    public String datoCondicionCliente;
    public String datoCodigo;
    public String datoDescripcion;
    public String datoCantidad;
    public String datoPrecioUnit;
    public String datoImpuestos;
    public String datoPrecioFinal;
    public String datoPrecioUnitVigente;
    public String datoPrecioTotal;
    public String datoDiaIngreso;
    public String datoMesIngreso;
    public String datoAnoIngreso;
    public String datoDiaVenta;
    public String datoMesVenta;
    public String datoAnoVenta;
    public String datoFechaOculta;
    public String datoCantidadVenta;
    public String datoCantidadVigente;
    public String datoCantidadModificada;
    public String datoHoraActual;
    public int ID;
    public int cantidad;
    public int cantidadVenta;
    public int cantidadVigente;
    public int cantidadModificada;
    public double impuestos;
    public double precioUnit;
    public double precioFinal;
    public double precioTotal;
    public double precioUnitVigente;

    public IngresarVentas() {
        // Propiedades del frame
        initComponents();
        setLayout(null);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/images/logos/logo.png")).getImage());

        // M??todos a ejecutar
        ocultarPaneles();
        fecha();
        leer();
    }

    // M??todo para ocultar los paneles del men?? principal, de ayuda y el mensaje de error
    public void ocultarPaneles() {
        panelStock.setVisible(false);
        panelCompras.setVisible(false);
        panelVentas.setVisible(false);
        panelGastos.setVisible(false);
        panelClientes.setVisible(false);
        panelProveedores.setVisible(false);
        panelFechaVentaInfo.setVisible(false);
        panelNroFacturaInfo.setVisible(false);
        panelCodigoClienteInfo.setVisible(false);
        panelNombreClienteInfo.setVisible(false);
        panelCondicionClienteInfo.setVisible(false);
        panelCodigoInfo.setVisible(false);
        panelDescripcionInfo.setVisible(false);
        panelCantidadInfo.setVisible(false);
        panelPrecioUnitInfo.setVisible(false);
        panelImpuestosInfo.setVisible(false);
        panelPrecioFinalInfo.setVisible(false);

        labelMensaje.setVisible(false);
    }

    // M??todo para obtener la fecha actual
    public void fecha() {
        Date sistFecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YY");
        campoFechaOculta.setText(formato.format(sistFecha));

        datoFechaOculta = campoFechaOculta.getText().trim();
        datoFechaVenta = campoFechaOculta.getText().trim();
        campoFechaVenta.setText(datoFechaVenta);
        campoFechaOculta.setVisible(false);
        
        Date date = Calendar.getInstance().getTime();
        String hour = date.toString();
        datoHoraActual = "" + hour.charAt(11) + hour.charAt(12) + hour.charAt(13) + hour.charAt(14) + hour.charAt(15);
    }

    // M??todo para leer el archivo "sales.mvp" que contiene la numeraci??n de las facturas de venta
    public void leer() {

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {

            archivo = new File("sales.mvp");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String FilaLeida;
            String[] CadenaSeparada;

            while ((FilaLeida = br.readLine()) != null) {

                CadenaSeparada = FilaLeida.split("-");
                
                datoLetraComprob = CadenaSeparada[0];
                datoPuntoVenta = CadenaSeparada[1];
                datoNroComprob = CadenaSeparada[2];
                
                int nroComprob = Integer.parseInt(datoNroComprob);
                nroComprob++;
        
                datoNroComprob = String.format("%8s", nroComprob).replace(' ','0');

                campoLetraComprob.setText(datoLetraComprob);
                campoPuntoVenta.setText(datoPuntoVenta);
                campoNroComprob.setText(datoNroComprob);

                br.close();
                fr.close();
            }

        } catch (Exception e) {

        } finally {

            // Accion a realizar luego de guardar el archivo
        }
    }

    // M??todo para leer el archivo "sales.mvp" que contiene la numeraci??n de las facturas de venta
    public void guardar() {

        String cadenaDatos = "";
        cadenaDatos = datoLetraComprob + "-" + datoPuntoVenta + "-" + datoNroComprob;

        FileWriter fichero;
        PrintWriter pw;

        try {

            fichero = new FileWriter("sales.mvp", false); // true para que creen nuevas lineas
            pw = new PrintWriter(fichero);
            pw.println(cadenaDatos);
            pw.close();
            fichero.close();

        } catch (Exception e) {

            JOptionPane.showConfirmDialog(null, "Error al guardar los datos!", "Informaci??n", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
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
        panelFechaVentaInfo = new javax.swing.JPanel();
        labelFechaVentaInfo = new javax.swing.JLabel();
        panelNroFacturaInfo = new javax.swing.JPanel();
        labelNroFacturaInfo = new javax.swing.JLabel();
        panelCodigoClienteInfo = new javax.swing.JPanel();
        labelCodigoClienteInfo = new javax.swing.JLabel();
        panelNombreClienteInfo = new javax.swing.JPanel();
        labelNombreClienteInfo = new javax.swing.JLabel();
        panelCondicionClienteInfo = new javax.swing.JPanel();
        labelCondicionClienteInfo1 = new javax.swing.JLabel();
        labelCondicionClienteInfo2 = new javax.swing.JLabel();
        labelCondicionClienteInfo3 = new javax.swing.JLabel();
        panelCodigoInfo = new javax.swing.JPanel();
        labelCodigoInfo = new javax.swing.JLabel();
        panelDescripcionInfo = new javax.swing.JPanel();
        labelDescripcionInfo = new javax.swing.JLabel();
        panelCantidadInfo = new javax.swing.JPanel();
        labelCantidadInfo = new javax.swing.JLabel();
        panelPrecioUnitInfo = new javax.swing.JPanel();
        labelPrecioUnitInfo = new javax.swing.JLabel();
        panelImpuestosInfo = new javax.swing.JPanel();
        labelImpuestosInfo = new javax.swing.JLabel();
        panelPrecioFinalInfo = new javax.swing.JPanel();
        labelPrecioFinalInfo = new javax.swing.JLabel();
        labelIngresar = new javax.swing.JLabel();
        labelDatosCliente = new javax.swing.JLabel();
        separadorCliente = new javax.swing.JSeparator();
        labelFechaVenta = new javax.swing.JLabel();
        labelNroFactura = new javax.swing.JLabel();
        labelCodigoCliente = new javax.swing.JLabel();
        labelNombreCliente = new javax.swing.JLabel();
        labelCondicionCliente = new javax.swing.JLabel();
        campoFechaVenta = new javax.swing.JTextField();
        campoLetraComprob = new javax.swing.JTextField();
        labelGuion1 = new javax.swing.JLabel();
        campoPuntoVenta = new javax.swing.JTextField();
        labelGuion2 = new javax.swing.JLabel();
        campoNroComprob = new javax.swing.JTextField();
        campoCodigoCliente = new javax.swing.JTextField();
        campoNombreCliente = new javax.swing.JTextField();
        campoCondicionCliente = new javax.swing.JTextField();
        fondoFechaVenta = new javax.swing.JLabel();
        fondoNroFactura = new javax.swing.JLabel();
        fondoCodigoCliente = new javax.swing.JLabel();
        fondoNombreCliente = new javax.swing.JLabel();
        fondoCondicionCliente = new javax.swing.JLabel();
        labelDatosProducto = new javax.swing.JLabel();
        separadorProducto = new javax.swing.JSeparator();
        labelCodigo = new javax.swing.JLabel();
        labelDescripcion = new javax.swing.JLabel();
        labelCantidad = new javax.swing.JLabel();
        labelPrecioUnit = new javax.swing.JLabel();
        labelImpuestos = new javax.swing.JLabel();
        labelPrecioFinal = new javax.swing.JLabel();
        campoCodigo = new javax.swing.JTextField();
        campoDescripcion = new javax.swing.JTextField();
        campoCantidad = new javax.swing.JTextField();
        campoPrecioUnit = new javax.swing.JTextField();
        campoImpuestos = new javax.swing.JTextField();
        campoPrecioFinal = new javax.swing.JTextField();
        fondoCodigo = new javax.swing.JLabel();
        fondoDescripcion = new javax.swing.JLabel();
        fondoCantidad = new javax.swing.JLabel();
        fondoPrecioUnit = new javax.swing.JLabel();
        fondoImpuestos = new javax.swing.JLabel();
        fondoPrecioFinal = new javax.swing.JLabel();
        labelMensaje = new javax.swing.JLabel();
        botonVolver = new javax.swing.JButton();
        botonLimpiar = new javax.swing.JButton();
        botonGuardar = new javax.swing.JButton();
        fondoPanelIngresar1 = new javax.swing.JLabel();
        fondoPanelIngresar2 = new javax.swing.JLabel();
        fondoIngresarVenta = new javax.swing.JLabel();
        campoFechaOculta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MS - Ingresar ventas");
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelCerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelCerrarMouseExited(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelCerrarMouseClicked(evt);
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

        panelFechaVentaInfo.setBackground(new java.awt.Color(0, 0, 0, 230));
        panelFechaVentaInfo.setLayout(null);

        labelFechaVentaInfo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelFechaVentaInfo.setForeground(new java.awt.Color(255, 180, 140));
        labelFechaVentaInfo.setText("Formato \"DD/MM/AA\"");
        panelFechaVentaInfo.add(labelFechaVentaInfo);
        labelFechaVentaInfo.setBounds(10, 5, 131, 17);

        panelFondoIngresar.add(panelFechaVentaInfo);
        panelFechaVentaInfo.setBounds(0, 60, 150, 25);

        panelNroFacturaInfo.setBackground(new java.awt.Color(0, 0, 0, 230));
        panelNroFacturaInfo.setLayout(null);

        labelNroFacturaInfo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelNroFacturaInfo.setForeground(new java.awt.Color(255, 180, 140));
        labelNroFacturaInfo.setText("Formato \"C-0001-00000001\"");
        panelNroFacturaInfo.add(labelNroFacturaInfo);
        labelNroFacturaInfo.setBounds(10, 5, 162, 17);

        panelFondoIngresar.add(panelNroFacturaInfo);
        panelNroFacturaInfo.setBounds(120, 60, 180, 25);

        panelCodigoClienteInfo.setBackground(new java.awt.Color(0, 0, 0, 230));
        panelCodigoClienteInfo.setLayout(null);

        labelCodigoClienteInfo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelCodigoClienteInfo.setForeground(new java.awt.Color(255, 180, 140));
        labelCodigoClienteInfo.setText("Hasta 12 letras y/o n??meros!");
        panelCodigoClienteInfo.add(labelCodigoClienteInfo);
        labelCodigoClienteInfo.setBounds(10, 5, 163, 17);

        panelFondoIngresar.add(panelCodigoClienteInfo);
        panelCodigoClienteInfo.setBounds(255, 60, 180, 25);

        panelNombreClienteInfo.setBackground(new java.awt.Color(0, 0, 0, 230));
        panelNombreClienteInfo.setLayout(null);

        labelNombreClienteInfo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelNombreClienteInfo.setForeground(new java.awt.Color(255, 180, 140));
        labelNombreClienteInfo.setText("Hasta 50 caracteres!");
        panelNombreClienteInfo.add(labelNombreClienteInfo);
        labelNombreClienteInfo.setBounds(10, 5, 120, 17);

        panelFondoIngresar.add(panelNombreClienteInfo);
        panelNombreClienteInfo.setBounds(430, 60, 140, 25);

        panelCondicionClienteInfo.setBackground(new java.awt.Color(0, 0, 0, 230));
        panelCondicionClienteInfo.setLayout(null);

        labelCondicionClienteInfo1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelCondicionClienteInfo1.setForeground(new java.awt.Color(255, 180, 140));
        labelCondicionClienteInfo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCondicionClienteInfo1.setText("\"CF: Cons. Final\"");
        panelCondicionClienteInfo.add(labelCondicionClienteInfo1);
        labelCondicionClienteInfo1.setBounds(10, 5, 110, 17);

        labelCondicionClienteInfo2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelCondicionClienteInfo2.setForeground(new java.awt.Color(255, 180, 140));
        labelCondicionClienteInfo2.setText("\"MT: Monotributo\"");
        labelCondicionClienteInfo2.setToolTipText("");
        panelCondicionClienteInfo.add(labelCondicionClienteInfo2);
        labelCondicionClienteInfo2.setBounds(10, 25, 110, 17);

        labelCondicionClienteInfo3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelCondicionClienteInfo3.setForeground(new java.awt.Color(255, 180, 140));
        labelCondicionClienteInfo3.setText("\"RI: IVA Inscripto\"");
        panelCondicionClienteInfo.add(labelCondicionClienteInfo3);
        labelCondicionClienteInfo3.setBounds(10, 45, 110, 17);

        panelFondoIngresar.add(panelCondicionClienteInfo);
        panelCondicionClienteInfo.setBounds(610, 15, 130, 70);

        panelCodigoInfo.setBackground(new java.awt.Color(0, 0, 0, 230));
        panelCodigoInfo.setLayout(null);

        labelCodigoInfo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelCodigoInfo.setForeground(new java.awt.Color(255, 180, 140));
        labelCodigoInfo.setText("Hasta 12 letras y/o n??meros!");
        panelCodigoInfo.add(labelCodigoInfo);
        labelCodigoInfo.setBounds(10, 5, 163, 17);

        panelFondoIngresar.add(panelCodigoInfo);
        panelCodigoInfo.setBounds(0, 170, 180, 25);

        panelDescripcionInfo.setBackground(new java.awt.Color(0, 0, 0, 230));
        panelDescripcionInfo.setLayout(null);

        labelDescripcionInfo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelDescripcionInfo.setForeground(new java.awt.Color(255, 180, 140));
        labelDescripcionInfo.setText("Hasta 50 caracteres!");
        panelDescripcionInfo.add(labelDescripcionInfo);
        labelDescripcionInfo.setBounds(10, 5, 120, 17);

        panelFondoIngresar.add(panelDescripcionInfo);
        panelDescripcionInfo.setBounds(160, 170, 140, 25);

        panelCantidadInfo.setBackground(new java.awt.Color(0, 0, 0, 100));
        panelCantidadInfo.setLayout(null);

        labelCantidadInfo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelCantidadInfo.setForeground(new java.awt.Color(255, 180, 140));
        labelCantidadInfo.setText("Hasta 9999999!");
        panelCantidadInfo.add(labelCantidadInfo);
        labelCantidadInfo.setBounds(10, 5, 90, 17);

        panelFondoIngresar.add(panelCantidadInfo);
        panelCantidadInfo.setBounds(360, 170, 110, 25);

        panelPrecioUnitInfo.setBackground(new java.awt.Color(0, 0, 0, 100));
        panelPrecioUnitInfo.setLayout(null);

        labelPrecioUnitInfo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelPrecioUnitInfo.setForeground(new java.awt.Color(255, 180, 140));
        labelPrecioUnitInfo.setText("Hasta 999999.99!");
        panelPrecioUnitInfo.add(labelPrecioUnitInfo);
        labelPrecioUnitInfo.setBounds(10, 5, 101, 17);

        panelFondoIngresar.add(panelPrecioUnitInfo);
        panelPrecioUnitInfo.setBounds(445, 170, 120, 25);

        panelImpuestosInfo.setBackground(new java.awt.Color(0, 0, 0, 100));
        panelImpuestosInfo.setLayout(null);

        labelImpuestosInfo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelImpuestosInfo.setForeground(new java.awt.Color(255, 180, 140));
        labelImpuestosInfo.setText("Hasta 999999.99!");
        panelImpuestosInfo.add(labelImpuestosInfo);
        labelImpuestosInfo.setBounds(10, 5, 101, 17);

        panelFondoIngresar.add(panelImpuestosInfo);
        panelImpuestosInfo.setBounds(525, 170, 120, 25);

        panelPrecioFinalInfo.setBackground(new java.awt.Color(0, 0, 0, 100));
        panelPrecioFinalInfo.setLayout(null);

        labelPrecioFinalInfo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        labelPrecioFinalInfo.setForeground(new java.awt.Color(255, 180, 140));
        labelPrecioFinalInfo.setText("Hasta 999999.99!");
        panelPrecioFinalInfo.add(labelPrecioFinalInfo);
        labelPrecioFinalInfo.setBounds(10, 5, 101, 17);

        panelFondoIngresar.add(panelPrecioFinalInfo);
        panelPrecioFinalInfo.setBounds(620, 170, 120, 25);

        labelIngresar.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        labelIngresar.setForeground(new java.awt.Color(255, 90, 0));
        labelIngresar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIngresar.setText("REGISTRAR VENTAS NUEVAS");
        panelFondoIngresar.add(labelIngresar);
        labelIngresar.setBounds(0, 15, 740, 40);

        labelDatosCliente.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelDatosCliente.setForeground(new java.awt.Color(255, 90, 0));
        labelDatosCliente.setText("Datos del cliente");
        panelFondoIngresar.add(labelDatosCliente);
        labelDatosCliente.setBounds(15, 60, 150, 23);

        separadorCliente.setBackground(new java.awt.Color(60, 60, 60));
        separadorCliente.setForeground(new java.awt.Color(40, 40, 40));
        separadorCliente.setPreferredSize(new java.awt.Dimension(720, 10));
        panelFondoIngresar.add(separadorCliente);
        separadorCliente.setBounds(10, 85, 720, 10);

        labelFechaVenta.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelFechaVenta.setForeground(new java.awt.Color(175, 175, 175));
        labelFechaVenta.setText("Fecha Venta");
        labelFechaVenta.setToolTipText("");
        labelFechaVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelFechaVentaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelFechaVentaMouseExited(evt);
            }
        });
        panelFondoIngresar.add(labelFechaVenta);
        labelFechaVenta.setBounds(15, 90, 90, 23);

        labelNroFactura.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelNroFactura.setForeground(new java.awt.Color(175, 175, 175));
        labelNroFactura.setText("Nro. Factura");
        labelNroFactura.setToolTipText("");
        labelNroFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelNroFacturaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelNroFacturaMouseExited(evt);
            }
        });
        panelFondoIngresar.add(labelNroFactura);
        labelNroFactura.setBounds(140, 90, 92, 23);

        labelCodigoCliente.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelCodigoCliente.setForeground(new java.awt.Color(175, 175, 175));
        labelCodigoCliente.setText("C??d. Cliente");
        labelCodigoCliente.setToolTipText("");
        labelCodigoCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelCodigoClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelCodigoClienteMouseExited(evt);
            }
        });
        panelFondoIngresar.add(labelCodigoCliente);
        labelCodigoCliente.setBounds(285, 90, 89, 23);

        labelNombreCliente.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelNombreCliente.setForeground(new java.awt.Color(175, 175, 175));
        labelNombreCliente.setText("Raz??n Social Cliente");
        labelNombreCliente.setToolTipText("");
        labelNombreCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelNombreClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelNombreClienteMouseExited(evt);
            }
        });
        panelFondoIngresar.add(labelNombreCliente);
        labelNombreCliente.setBounds(420, 90, 147, 23);

        labelCondicionCliente.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelCondicionCliente.setForeground(new java.awt.Color(175, 175, 175));
        labelCondicionCliente.setText("Cond. Cliente");
        labelCondicionCliente.setToolTipText("");
        labelCondicionCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelCondicionClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelCondicionClienteMouseExited(evt);
            }
        });
        panelFondoIngresar.add(labelCondicionCliente);
        labelCondicionCliente.setBounds(635, 90, 98, 23);

        campoFechaVenta.setBackground(new java.awt.Color(0, 0, 0));
        campoFechaVenta.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        campoFechaVenta.setForeground(new java.awt.Color(175, 175, 175));
        campoFechaVenta.setBorder(null);
        campoFechaVenta.setCaretColor(new java.awt.Color(175, 175, 175));
        campoFechaVenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoFechaVentaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoFechaVentaFocusLost(evt);
            }
        });
        campoFechaVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoFechaVentaKeyTyped(evt);
            }
        });
        panelFondoIngresar.add(campoFechaVenta);
        campoFechaVenta.setBounds(15, 120, 110, 30);

        campoLetraComprob.setBackground(new java.awt.Color(0, 0, 0));
        campoLetraComprob.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        campoLetraComprob.setForeground(new java.awt.Color(175, 175, 175));
        campoLetraComprob.setToolTipText("");
        campoLetraComprob.setBorder(null);
        campoLetraComprob.setCaretColor(new java.awt.Color(175, 175, 175));
        campoLetraComprob.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoLetraComprobFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoLetraComprobFocusLost(evt);
            }
        });
        campoLetraComprob.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoLetraComprobKeyTyped(evt);
            }
        });
        panelFondoIngresar.add(campoLetraComprob);
        campoLetraComprob.setBounds(140, 120, 10, 30);

        labelGuion1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelGuion1.setForeground(new java.awt.Color(175, 175, 175));
        labelGuion1.setText("-");
        labelGuion1.setToolTipText("");
        panelFondoIngresar.add(labelGuion1);
        labelGuion1.setBounds(150, 124, 6, 20);

        campoPuntoVenta.setBackground(new java.awt.Color(0, 0, 0));
        campoPuntoVenta.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        campoPuntoVenta.setForeground(new java.awt.Color(175, 175, 175));
        campoPuntoVenta.setBorder(null);
        campoPuntoVenta.setCaretColor(new java.awt.Color(175, 175, 175));
        campoPuntoVenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoPuntoVentaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoPuntoVentaFocusLost(evt);
            }
        });
        campoPuntoVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoPuntoVentaKeyTyped(evt);
            }
        });
        panelFondoIngresar.add(campoPuntoVenta);
        campoPuntoVenta.setBounds(156, 120, 36, 30);

        labelGuion2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelGuion2.setForeground(new java.awt.Color(175, 175, 175));
        labelGuion2.setText("-");
        labelGuion2.setToolTipText("");
        panelFondoIngresar.add(labelGuion2);
        labelGuion2.setBounds(192, 124, 6, 20);

        campoNroComprob.setBackground(new java.awt.Color(0, 0, 0));
        campoNroComprob.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        campoNroComprob.setForeground(new java.awt.Color(175, 175, 175));
        campoNroComprob.setBorder(null);
        campoNroComprob.setCaretColor(new java.awt.Color(175, 175, 175));
        campoNroComprob.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoNroComprobFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoNroComprobFocusLost(evt);
            }
        });
        campoNroComprob.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoNroComprobKeyTyped(evt);
            }
        });
        panelFondoIngresar.add(campoNroComprob);
        campoNroComprob.setBounds(198, 120, 72, 30);

        campoCodigoCliente.setBackground(new java.awt.Color(0, 0, 0));
        campoCodigoCliente.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        campoCodigoCliente.setForeground(new java.awt.Color(175, 175, 175));
        campoCodigoCliente.setBorder(null);
        campoCodigoCliente.setCaretColor(new java.awt.Color(175, 175, 175));
        campoCodigoCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoCodigoClienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoCodigoClienteFocusLost(evt);
            }
        });
        campoCodigoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoCodigoClienteKeyTyped(evt);
            }
        });
        panelFondoIngresar.add(campoCodigoCliente);
        campoCodigoCliente.setBounds(285, 120, 120, 30);

        campoNombreCliente.setBackground(new java.awt.Color(0, 0, 0));
        campoNombreCliente.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        campoNombreCliente.setForeground(new java.awt.Color(175, 175, 175));
        campoNombreCliente.setBorder(null);
        campoNombreCliente.setCaretColor(new java.awt.Color(175, 175, 175));
        campoNombreCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoNombreClienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoNombreClienteFocusLost(evt);
            }
        });
        campoNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoNombreClienteKeyTyped(evt);
            }
        });
        panelFondoIngresar.add(campoNombreCliente);
        campoNombreCliente.setBounds(420, 120, 215, 30);

        campoCondicionCliente.setBackground(new java.awt.Color(0, 0, 0));
        campoCondicionCliente.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        campoCondicionCliente.setForeground(new java.awt.Color(175, 175, 175));
        campoCondicionCliente.setBorder(null);
        campoCondicionCliente.setCaretColor(new java.awt.Color(175, 175, 175));
        campoCondicionCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoCondicionClienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoCondicionClienteFocusLost(evt);
            }
        });
        campoCondicionCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoCondicionClienteKeyTyped(evt);
            }
        });
        panelFondoIngresar.add(campoCondicionCliente);
        campoCondicionCliente.setBounds(650, 120, 75, 30);

        fondoFechaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-fechacompra1.png"))); // NOI18N
        panelFondoIngresar.add(fondoFechaVenta);
        fondoFechaVenta.setBounds(10, 115, 120, 40);

        fondoNroFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-nrofactura1.png"))); // NOI18N
        panelFondoIngresar.add(fondoNroFactura);
        fondoNroFactura.setBounds(135, 115, 140, 40);

        fondoCodigoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-codigoprov1.png"))); // NOI18N
        panelFondoIngresar.add(fondoCodigoCliente);
        fondoCodigoCliente.setBounds(280, 115, 130, 40);

        fondoNombreCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-nombreprov1.png"))); // NOI18N
        panelFondoIngresar.add(fondoNombreCliente);
        fondoNombreCliente.setBounds(415, 115, 225, 40);

        fondoCondicionCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-condicionprov1.png"))); // NOI18N
        panelFondoIngresar.add(fondoCondicionCliente);
        fondoCondicionCliente.setBounds(645, 115, 85, 40);

        labelDatosProducto.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelDatosProducto.setForeground(new java.awt.Color(255, 90, 0));
        labelDatosProducto.setText("Datos del producto");
        panelFondoIngresar.add(labelDatosProducto);
        labelDatosProducto.setBounds(15, 170, 140, 23);

        separadorProducto.setBackground(new java.awt.Color(60, 60, 60));
        separadorProducto.setForeground(new java.awt.Color(40, 40, 40));
        separadorProducto.setPreferredSize(new java.awt.Dimension(720, 10));
        panelFondoIngresar.add(separadorProducto);
        separadorProducto.setBounds(10, 195, 720, 10);

        labelCodigo.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelCodigo.setForeground(new java.awt.Color(175, 175, 175));
        labelCodigo.setText("C??d. Producto");
        labelCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelCodigoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelCodigoMouseExited(evt);
            }
        });
        panelFondoIngresar.add(labelCodigo);
        labelCodigo.setBounds(15, 200, 105, 23);

        labelDescripcion.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelDescripcion.setForeground(new java.awt.Color(175, 175, 175));
        labelDescripcion.setText("Descripci??n Producto");
        labelDescripcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelDescripcionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelDescripcionMouseExited(evt);
            }
        });
        panelFondoIngresar.add(labelDescripcion);
        labelDescripcion.setBounds(150, 200, 156, 23);

        labelCantidad.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelCantidad.setForeground(new java.awt.Color(175, 175, 175));
        labelCantidad.setText("Cantidad");
        labelCantidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelCantidadMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelCantidadMouseExited(evt);
            }
        });
        panelFondoIngresar.add(labelCantidad);
        labelCantidad.setBounds(380, 200, 65, 23);

        labelPrecioUnit.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelPrecioUnit.setForeground(new java.awt.Color(175, 175, 175));
        labelPrecioUnit.setText("Precio Unit.");
        labelPrecioUnit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelPrecioUnitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelPrecioUnitMouseExited(evt);
            }
        });
        panelFondoIngresar.add(labelPrecioUnit);
        labelPrecioUnit.setBounds(457, 200, 86, 23);

        labelImpuestos.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelImpuestos.setForeground(new java.awt.Color(175, 175, 175));
        labelImpuestos.setText("Imp./Desc.");
        labelImpuestos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelImpuestosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelImpuestosMouseExited(evt);
            }
        });
        panelFondoIngresar.add(labelImpuestos);
        labelImpuestos.setBounds(548, 200, 80, 23);

        labelPrecioFinal.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelPrecioFinal.setForeground(new java.awt.Color(175, 175, 175));
        labelPrecioFinal.setText("Precio Final");
        labelPrecioFinal.setToolTipText("");
        labelPrecioFinal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelPrecioFinalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelPrecioFinalMouseExited(evt);
            }
        });
        panelFondoIngresar.add(labelPrecioFinal);
        labelPrecioFinal.setBounds(638, 200, 84, 23);

        campoCodigo.setBackground(new java.awt.Color(0, 0, 0));
        campoCodigo.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        campoCodigo.setForeground(new java.awt.Color(175, 175, 175));
        campoCodigo.setBorder(null);
        campoCodigo.setCaretColor(new java.awt.Color(175, 175, 175));
        campoCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoCodigoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoCodigoFocusLost(evt);
            }
        });
        campoCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoCodigoKeyTyped(evt);
            }
        });
        panelFondoIngresar.add(campoCodigo);
        campoCodigo.setBounds(15, 230, 120, 30);

        campoDescripcion.setBackground(new java.awt.Color(0, 0, 0));
        campoDescripcion.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        campoDescripcion.setForeground(new java.awt.Color(175, 175, 175));
        campoDescripcion.setBorder(null);
        campoDescripcion.setCaretColor(new java.awt.Color(175, 175, 175));
        campoDescripcion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoDescripcionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoDescripcionFocusLost(evt);
            }
        });
        campoDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoDescripcionKeyTyped(evt);
            }
        });
        panelFondoIngresar.add(campoDescripcion);
        campoDescripcion.setBounds(150, 230, 215, 30);

        campoCantidad.setBackground(new java.awt.Color(0, 0, 0));
        campoCantidad.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        campoCantidad.setForeground(new java.awt.Color(175, 175, 175));
        campoCantidad.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        campoCantidad.setText("0");
        campoCantidad.setBorder(null);
        campoCantidad.setCaretColor(new java.awt.Color(175, 175, 175));
        campoCantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoCantidadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoCantidadFocusLost(evt);
            }
        });
        campoCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoCantidadKeyTyped(evt);
            }
        });
        panelFondoIngresar.add(campoCantidad);
        campoCantidad.setBounds(380, 230, 70, 30);

        campoPrecioUnit.setBackground(new java.awt.Color(0, 0, 0));
        campoPrecioUnit.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        campoPrecioUnit.setForeground(new java.awt.Color(175, 175, 175));
        campoPrecioUnit.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        campoPrecioUnit.setText("0.00");
        campoPrecioUnit.setToolTipText("");
        campoPrecioUnit.setBorder(null);
        campoPrecioUnit.setCaretColor(new java.awt.Color(175, 175, 175));
        campoPrecioUnit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoPrecioUnitFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoPrecioUnitFocusLost(evt);
            }
        });
        campoPrecioUnit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoPrecioUnitKeyTyped(evt);
            }
        });
        panelFondoIngresar.add(campoPrecioUnit);
        campoPrecioUnit.setBounds(465, 230, 70, 30);

        campoImpuestos.setBackground(new java.awt.Color(0, 0, 0));
        campoImpuestos.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        campoImpuestos.setForeground(new java.awt.Color(175, 175, 175));
        campoImpuestos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        campoImpuestos.setText("0.00");
        campoImpuestos.setBorder(null);
        campoImpuestos.setCaretColor(new java.awt.Color(175, 175, 175));
        campoImpuestos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoImpuestosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoImpuestosFocusLost(evt);
            }
        });
        campoImpuestos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoImpuestosKeyTyped(evt);
            }
        });
        panelFondoIngresar.add(campoImpuestos);
        campoImpuestos.setBounds(550, 230, 70, 30);

        campoPrecioFinal.setBackground(new java.awt.Color(0, 0, 0));
        campoPrecioFinal.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        campoPrecioFinal.setForeground(new java.awt.Color(175, 175, 175));
        campoPrecioFinal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        campoPrecioFinal.setText("0.00");
        campoPrecioFinal.setToolTipText("");
        campoPrecioFinal.setBorder(null);
        campoPrecioFinal.setCaretColor(new java.awt.Color(175, 175, 175));
        campoPrecioFinal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoPrecioFinalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoPrecioFinalFocusLost(evt);
            }
        });
        campoPrecioFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoPrecioFinalKeyTyped(evt);
            }
        });
        panelFondoIngresar.add(campoPrecioFinal);
        campoPrecioFinal.setBounds(635, 230, 90, 30);

        fondoCodigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-codigo1.png"))); // NOI18N
        panelFondoIngresar.add(fondoCodigo);
        fondoCodigo.setBounds(10, 225, 130, 40);

        fondoDescripcion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-descripcion3.png"))); // NOI18N
        panelFondoIngresar.add(fondoDescripcion);
        fondoDescripcion.setBounds(145, 225, 225, 40);

        fondoCantidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-cantidad1.png"))); // NOI18N
        panelFondoIngresar.add(fondoCantidad);
        fondoCantidad.setBounds(375, 225, 80, 40);

        fondoPrecioUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-moneda1.png"))); // NOI18N
        panelFondoIngresar.add(fondoPrecioUnit);
        fondoPrecioUnit.setBounds(460, 225, 80, 40);

        fondoImpuestos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-preciofinal1.png"))); // NOI18N
        panelFondoIngresar.add(fondoImpuestos);
        fondoImpuestos.setBounds(545, 225, 80, 40);

        fondoPrecioFinal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-impuestos1.png"))); // NOI18N
        panelFondoIngresar.add(fondoPrecioFinal);
        fondoPrecioFinal.setBounds(630, 225, 100, 40);

        labelMensaje.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelMensaje.setForeground(new java.awt.Color(255, 90, 0));
        labelMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensaje.setToolTipText("");
        panelFondoIngresar.add(labelMensaje);
        labelMensaje.setBounds(0, 270, 740, 30);

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
        botonVolver.setBounds(135, 310, 130, 35);

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
        botonLimpiar.setBounds(305, 310, 130, 35);

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
        botonGuardar.setBounds(475, 310, 130, 35);

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

        fondoIngresarVenta.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        fondoIngresarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondos/fondo2.png"))); // NOI18N
        fondoIngresarVenta.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        fondoIngresarVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fondoIngresarVentaMouseClicked(evt);
            }
        });
        panelIngresar.add(fondoIngresarVenta);
        fondoIngresarVenta.setBounds(0, 0, 800, 500);
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

    private void botonLimpiarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonLimpiarMouseEntered
        botonLimpiar.setForeground(new java.awt.Color(255, 90, 0));
    }//GEN-LAST:event_botonLimpiarMouseEntered

    private void botonLimpiarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonLimpiarMouseExited
        botonLimpiar.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_botonLimpiarMouseExited

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonVolverActionPerformed

    private void botonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarActionPerformed
        // Limpia los campos, la visibilidad del mensaje de error y recupera el foco
        campoFechaVenta.setText(datoFechaOculta);
        campoCodigoCliente.setText("");
        campoNombreCliente.setText("");
        campoCondicionCliente.setText("");
        campoCodigo.setText("");
        campoDescripcion.setText("");
        campoCantidad.setText("0");
        campoPrecioUnit.setText("0.00");
        campoImpuestos.setText("0.00");
        campoPrecioFinal.setText("0.00");
        
        leer();

        campoFechaVenta.requestFocus();

        labelMensaje.setVisible(false);
    }//GEN-LAST:event_botonLimpiarActionPerformed

    private void fondoIngresarVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fondoIngresarVentaMouseClicked
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
    }//GEN-LAST:event_fondoIngresarVentaMouseClicked

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
        datoFechaVenta = campoFechaVenta.getText().trim();
        datoLetraComprob = campoLetraComprob.getText().trim();
        datoPuntoVenta = campoPuntoVenta.getText().trim();
        datoNroComprob = campoNroComprob.getText().trim();
        datoNroFactura = campoLetraComprob.getText().trim() + "-" + campoPuntoVenta.getText().trim() + "-" + campoNroComprob.getText().trim();
        datoCodigoCliente = campoCodigoCliente.getText().trim();
        datoNombreCliente = campoNombreCliente.getText();
        datoCondicionCliente = campoCondicionCliente.getText().trim();
        datoCodigo = campoCodigo.getText().trim();
        datoDescripcion = campoDescripcion.getText();
        datoCantidad = campoCantidad.getText().trim();
        datoPrecioUnit = campoPrecioUnit.getText().trim();
        datoImpuestos = campoImpuestos.getText().trim();
        datoPrecioFinal = campoPrecioFinal.getText().trim();
        datoFechaOculta = campoFechaOculta.getText().trim();
        datoDiaIngreso = datoFechaOculta.charAt(0) + "" + datoFechaOculta.charAt(1);
        datoMesIngreso = datoFechaOculta.charAt(3) + "" + datoFechaOculta.charAt(4);
        datoAnoIngreso = datoFechaOculta.charAt(6) + "" + datoFechaOculta.charAt(7);
        datoDiaVenta = datoFechaVenta.charAt(0) + "" + datoFechaVenta.charAt(1);
        datoMesVenta = datoFechaVenta.charAt(3) + "" + datoFechaVenta.charAt(4);
        datoAnoVenta = datoFechaVenta.charAt(6) + "" + datoFechaVenta.charAt(7);

        // Establece el formato de la fecha para que se ingrese correctamente
        String formatoFechaCompra = "\\d{1,2}/\\d{1,2}/\\d{2}";

        // Contin??a si ning??n campo qued?? sin datos o con datos incompletos o erroneos
        if (datoFechaVenta.length() == 8 && datoFechaVenta.matches(formatoFechaCompra) && datoNroFactura.length() == 15
                && !datoCodigoCliente.equals("") && !datoNombreCliente.equals("") && datoCondicionCliente.length() == 2 && !datoCodigo.equals("")
                && !datoDescripcion.equals("") && !datoCantidad.equals("") && !datoPrecioUnit.equals("") && datoPrecioUnit.contains(".")
                && !datoImpuestos.equals("") && datoImpuestos.contains(".") && !datoPrecioFinal.equals("") && datoPrecioFinal.contains(".")) {

            try {
                // Establece conexi??n con DB para consultar por nro de factura si ya fue ingresada anteriormente
                Connection con = Conexion.conectar();
                PreparedStatement sta = con.prepareStatement("select * from ventas where nro_factura = '" + datoNroFactura + "'");
                ResultSet res = sta.executeQuery();

                if (res.next()) {

                    // Si fue creada anteriormente la factura, muestra un mensaje de error
                    labelMensaje.setForeground(new java.awt.Color(255, 0, 0));
                    labelMensaje.setText("La venta que intenta registrar ya fue registrada anteriormente!");
                    labelMensaje.setVisible(true);

                    con.close();

                } else {

                    try {
                        // Si no fue creada la factura, establece conexi??n con DB para consultar si el cliente ya fue creado tambi??n
                        Connection con1 = Conexion.conectar();
                        PreparedStatement sta1 = con1.prepareStatement("select * from clientes where codigo = '" + datoCodigoCliente + "'");
                        ResultSet res1 = sta1.executeQuery();

                        if (res1.next()) {

                            // Si el cliente est?? en la base de clientes, contin??a
                            con1.close();

                        } else {
                            // Si el cliente no fue creado, lo agrega a la base de clientes
                            Connection con2 = Conexion.conectar();
                            PreparedStatement sta2 = con2.prepareStatement("insert into clientes "
                                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                            
                            sta2.setInt(1, 0);
                            sta2.setString(2, datoCodigoCliente);
                            sta2.setString(3, datoNombreCliente);
                            sta2.setString(4, datoCondicionCliente);
                            sta2.setString(5, "");
                            sta2.setString(6, "");
                            sta2.setString(7, "");
                            sta2.setString(8, "");
                            sta2.setString(9, "");
                            sta2.setString(10, "");
                            sta2.setString(11, datoFechaOculta);
                            sta2.setString(12, datoHoraActual);
                            sta2.setString(13, datoFechaOculta);
                            sta2.setString(14, datoHoraActual);
                            sta2.setString(15, datoDiaIngreso);
                            sta2.setString(16, datoMesIngreso);
                            sta2.setString(17, datoAnoIngreso);
                            sta2.setString(18, datoDiaIngreso);
                            sta2.setString(19, datoMesIngreso);
                            sta2.setString(20, datoAnoIngreso);

                            sta2.executeUpdate();
                            con2.close();
                            con1.close();
                        }

                    } catch (SQLException e) {
                        // Si no se conecta con la DB, muestra un mensaje de error
                        JOptionPane.showConfirmDialog(null, "Error de conexi??n: Base clientes!", "Informaci??n", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    }

                    try {
                        // Establece conexi??n con DB para consultar si el producto fue creado anteriormente
                        Connection con1 = Conexion.conectar();
                        PreparedStatement sta1 = con1.prepareStatement("select * from stock where codigo_stock = '" + datoCodigo + "'");
                        ResultSet res1 = sta1.executeQuery();

                        if (res1.next()) {
                            // Si encuentra el c??digo en la base de stock, actualiza el stock en la base de stock
                            datoCantidadVigente = res1.getString("cantidad");
                            datoPrecioUnitVigente = res1.getString("precio_unit");

                            precioUnitVigente = Double.parseDouble(datoPrecioUnitVigente);
                            cantidadVigente = Integer.parseInt(datoPrecioUnitVigente);
                            
                            cantidadModificada = cantidadVigente - cantidad;
                            datoCantidadModificada = cantidadModificada + "";
                            precioTotal = cantidadModificada * precioUnitVigente;
                            datoPrecioTotal = String.format("%.2f", precioTotal).replace(',', '.');

                            Connection con2 = Conexion.conectar();
                            PreparedStatement sta2 = con2.prepareStatement("update stock set cantidad=?, precio_total=? "
                                    + "where codigo_stock = '" + datoCodigo + "'");
                            
                            sta2.setString(1, datoCantidadModificada);
                            sta2.setString(2, datoPrecioTotal);

                            sta2.executeUpdate();
                            con2.close();
                            con1.close();

                        } else {
                            // Si no encuentra el c??digo en la base de stock, lo almacena
                            Connection con3 = Conexion.conectar();
                            PreparedStatement sta3 = con3.prepareStatement("insert into stock values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                            sta3.setInt(1, 0);
                            sta3.setString(2, datoCodigo);
                            sta3.setString(3, datoDescripcion);
                            sta3.setString(4, datoCantidad);
                            sta3.setString(5, "ARS");
                            sta3.setString(6, datoPrecioUnit);
                            sta3.setString(7, datoPrecioFinal);
                            sta3.setString(8, datoFechaOculta);
                            sta3.setString(9, datoHoraActual);
                            sta3.setString(10, datoFechaOculta);
                            sta3.setString(11, datoHoraActual);
                            sta3.setString(12, datoDiaIngreso);
                            sta3.setString(13, datoMesIngreso);
                            sta3.setString(14, datoAnoIngreso);
                            sta3.setString(15, datoDiaIngreso);
                            sta3.setString(16, datoMesIngreso);
                            sta3.setString(17, datoAnoIngreso);

                            sta3.executeUpdate();
                            con3.close();
                            con1.close();
                        }

                    } catch (SQLException e) {
                        // Si no se conecta con la DB, muestra un mensaje de error
                        JOptionPane.showConfirmDialog(null, "Error de conexi??n: Base stock!", "Informaci??n", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    }

                    try {
                        // Establece conexi??n con DB para registrar finalmente la venta
                        Connection con1 = Conexion.conectar();
                        PreparedStatement sta1 = con1.prepareStatement("insert into ventas "
                                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                        sta1.setInt(1, 0);
                        sta1.setString(2, datoFechaVenta);
                        sta1.setString(3, datoFechaOculta);
                        sta1.setString(4, datoFechaOculta);
                        sta1.setString(5, datoHoraActual);
                        sta1.setString(6, datoNroFactura);
                        sta1.setString(7, datoCodigoCliente);
                        sta1.setString(8, datoNombreCliente);
                        sta1.setString(9, datoCondicionCliente);
                        sta1.setString(10, datoCodigo);
                        sta1.setString(11, datoDescripcion);
                        sta1.setString(12, datoCantidad);
                        sta1.setString(13, datoPrecioUnit);
                        sta1.setString(14, datoImpuestos);
                        sta1.setString(15, datoPrecioFinal);
                        sta1.setString(16, datoDiaIngreso);
                        sta1.setString(17, datoMesIngreso);
                        sta1.setString(18, datoAnoIngreso);
                        sta1.setString(19, datoDiaVenta);
                        sta1.setString(20, datoMesVenta);
                        sta1.setString(21, datoAnoVenta);
                        sta1.setString(22, datoDiaIngreso);
                        sta1.setString(23, datoMesIngreso);
                        sta1.setString(24, datoAnoIngreso);

                        sta1.executeUpdate();

                        // Mensaje de venta registrada correctamente
                        labelMensaje.setForeground(new java.awt.Color(0, 255, 0));
                        labelMensaje.setText("La factura se registr?? exitosamente!");
                        labelMensaje.setVisible(true);

                        // Limpia los campos para una nueva registraci??n
                        campoFechaVenta.setText(datoFechaOculta);
                        campoLetraComprob.setText("");
                        campoPuntoVenta.setText("");
                        campoNroComprob.setText("");
                        campoCodigoCliente.setText("");
                        campoNombreCliente.setText("");
                        campoCondicionCliente.setText("");
                        campoCodigo.setText("");
                        campoDescripcion.setText("");
                        campoCantidad.setText("0");
                        campoPrecioUnit.setText("0.00");
                        campoImpuestos.setText("0.00");
                        campoPrecioFinal.setText("0.00");

                        campoFechaVenta.requestFocus();
                        
                        // Procede a guardar el archivo "data.txt" y leerlo, sumando una unidad al n??mero de factura
                        guardar();
                        leer();
                        
                        con1.close();

                    } catch (SQLException e) {
                        // Si no se conecta con la DB, muestra un mensaje de error
                        JOptionPane.showConfirmDialog(null, "Error de conexi??n: Base ventas!", "Informaci??n", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    }
                    // Cierra conexi??n definitiva con la DB
                    con.close();
                }

            } catch (SQLException e) {
                // Si no se conecta con la DB, muestra un mensaje de error
                JOptionPane.showConfirmDialog(null, "Error de conexi??n: Base ventas!", "Informaci??n", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            // Si alg??n campo est?? vac??o o incompleto, muestra un mensaje de error
            labelMensaje.setForeground(new java.awt.Color(255, 0, 0));
            labelMensaje.setText("Por favor, revise los datos ingresados!");
            labelMensaje.setVisible(true);
        }
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void campoFechaVentaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoFechaVentaFocusGained
        fondoFechaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-fechacompra2.png")));
        labelFechaVenta.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_campoFechaVentaFocusGained

    private void campoFechaVentaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoFechaVentaFocusLost
        fondoFechaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-fechacompra1.png")));
        labelFechaVenta.setForeground(new java.awt.Color(175, 175, 175));

        labelMensaje.setVisible(false);
    }//GEN-LAST:event_campoFechaVentaFocusLost

    private void campoLetraComprobFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoLetraComprobFocusGained
        fondoNroFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-nrofactura2.png")));
        labelNroFactura.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_campoLetraComprobFocusGained

    private void campoLetraComprobFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoLetraComprobFocusLost
        fondoNroFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-nrofactura1.png")));
        labelNroFactura.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_campoLetraComprobFocusLost

    private void campoCodigoClienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoCodigoClienteFocusGained
        fondoCodigoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-codigoprov2.png")));
        labelCodigoCliente.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_campoCodigoClienteFocusGained

    private void campoCodigoClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoCodigoClienteFocusLost
        fondoCodigoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-codigoprov1.png")));
        labelCodigoCliente.setForeground(new java.awt.Color(175, 175, 175));

        // Obtengo los datos de los campos de texto
        datoCodigoCliente = campoCodigoCliente.getText().trim();
        datoNombreCliente = campoNombreCliente.getText();

        // Contin??a si el campo c??digo no est?? vac??o
        if (!datoCodigoCliente.equals("")) {

            try {
                // Establece conexi??n con DB para consultar el c??digo ingresado
                Connection con = Conexion.conectar();
                PreparedStatement sta = con.prepareStatement("select * from clientes where codigo = '" + datoCodigoCliente + "'");
                ResultSet res = sta.executeQuery();

                if (res.next()) {
                    // Si el cliente ya fue creado, obtiene el resto de los datos
                    campoNombreCliente.setText(res.getString("razon_social"));
                    campoCondicionCliente.setText(res.getString("condicion"));

                    campoCodigo.requestFocus();

                    labelMensaje.setVisible(false);

                    con.close();

                } else {
                    // Muestra un mensaje de advertencia sobre un nuevo cliente
                    labelMensaje.setForeground(new java.awt.Color(255, 90, 0));
                    labelMensaje.setText("El cliente ingresado es nuevo. Se grabar?? al registrar la venta!");
                    labelMensaje.setVisible(true);

                    con.close();
                }

            } catch (SQLException e) {
                // Si no se conecta con la DB, muestra un mensaje de error
                JOptionPane.showConfirmDialog(null, "Error de conexi??n: Base clientes!", "Informaci??n", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_campoCodigoClienteFocusLost

    private void campoNombreClienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoNombreClienteFocusGained
        fondoNombreCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-nombreprov2.png")));
        labelNombreCliente.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_campoNombreClienteFocusGained

    private void campoNombreClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoNombreClienteFocusLost
        fondoNombreCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-nombreprov1.png")));
        labelNombreCliente.setForeground(new java.awt.Color(175, 175, 175));

        // Obtengo los datos de los campos de texto
        datoCodigoCliente = campoCodigoCliente.getText().trim();
        datoNombreCliente = campoNombreCliente.getText();

        // Contin??a si el campo nombre no est?? vac??o
        if (!datoNombreCliente.equals("")) {

            try {
                // Establece conexi??n con DB para consultar el nombre ingresado
                Connection con = Conexion.conectar();
                PreparedStatement sta = con.prepareStatement("select * from clientes where razon_social = '" + datoNombreCliente + "'");
                ResultSet res = sta.executeQuery();

                if (res.next()) {
                    // Si el cliente ya fue creado, obtiene el resto de los datos
                    campoCodigoCliente.setText(res.getString("codigo"));
                    campoCondicionCliente.setText(res.getString("condicion"));

                    campoCodigo.requestFocus();

                    labelMensaje.setVisible(false);

                    con.close();

                } else {
                    // Muestra un mensaje de advertencia sobre un nuevo cliente
                    labelMensaje.setForeground(new java.awt.Color(255, 90, 0));
                    labelMensaje.setText("El cliente ingresado es nuevo. Se grabar?? al registrar la venta!");
                    labelMensaje.setVisible(true);

                    con.close();
                }

            } catch (SQLException e) {
                // Si no se conecta con la DB, muestra un mensaje de error
                JOptionPane.showConfirmDialog(null, "Error de conexi??n: Base clientes!", "Informaci??n", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_campoNombreClienteFocusLost

    private void campoCondicionClienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoCondicionClienteFocusGained
        fondoCondicionCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-condicionprov2.png")));
        labelCondicionCliente.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_campoCondicionClienteFocusGained

    private void campoCondicionClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoCondicionClienteFocusLost
        fondoCondicionCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-condicionprov1.png")));
        labelCondicionCliente.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_campoCondicionClienteFocusLost

    private void campoCodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoCodigoFocusGained
        fondoCodigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-codigo2.png")));
        labelCodigo.setForeground(new java.awt.Color(255, 180, 140));

        labelMensaje.setVisible(false);
    }//GEN-LAST:event_campoCodigoFocusGained

    private void campoCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoCodigoFocusLost
        fondoCodigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-codigo1.png")));
        labelCodigo.setForeground(new java.awt.Color(175, 175, 175));

        // Obtengo los datos de los campos de texto
        datoCodigo = campoCodigo.getText().trim();
        datoDescripcion = campoDescripcion.getText();

        // Contin??a si el campo c??digo no est?? vac??o
        if (!datoCodigo.equals("")) {

            try {
                // Establece conexi??n con DB para consultar el c??digo ingresado
                Connection con = Conexion.conectar();
                PreparedStatement sta = con.prepareStatement("select * from stock where codigo_stock = '" + datoCodigo + "'");
                ResultSet res = sta.executeQuery();

                if (res.next()) {
                    // Si el producto ya fue creado, obtiene el resto de los datos
                    campoDescripcion.setText(res.getString("descripcion_stock"));
                    campoPrecioUnit.setText(res.getString("precio_unit"));

                    campoCantidad.requestFocus();

                    labelMensaje.setVisible(false);

                    con.close();

                } else {
                    // Muestra un mensaje de advertencia sobre un nuevo producto
                    labelMensaje.setForeground(new java.awt.Color(255, 90, 0));
                    labelMensaje.setText("El producto ingresado es nuevo. Se grabar?? al registrar la venta!");
                    labelMensaje.setVisible(true);

                    con.close();
                }

            } catch (SQLException e) {
                // Si no se conecta con la DB, muestra un mensaje de error
                JOptionPane.showConfirmDialog(null, "Error de conexi??n: Base stock!", "Informaci??n", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_campoCodigoFocusLost

    private void campoDescripcionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDescripcionFocusGained
        fondoDescripcion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-descripcion4.png")));
        labelDescripcion.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_campoDescripcionFocusGained

    private void campoDescripcionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDescripcionFocusLost
        fondoDescripcion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-descripcion3.png")));
        labelDescripcion.setForeground(new java.awt.Color(175, 175, 175));

        // Obtengo los datos de los campos de texto
        datoCodigo = campoCodigo.getText().trim();
        datoDescripcion = campoDescripcion.getText();

        // Contin??a si el campo descripci??n no est?? vac??o
        if (!datoDescripcion.equals("")) {

            try {
                // Establece conexi??n con DB para consultar la descripci??n ingresada
                Connection con = Conexion.conectar();
                PreparedStatement sta = con.prepareStatement("select * from stock where descripcion_stock = '" + datoDescripcion + "'");
                ResultSet res = sta.executeQuery();

                if (res.next()) {
                    // Si el producto ya fue creado, obtiene el resto de los datos
                    campoCodigo.setText(res.getString("codigo_stock"));
                    campoPrecioUnit.setText(res.getString("precio_unit"));

                    campoCantidad.requestFocus();

                    labelMensaje.setVisible(false);

                    con.close();

                } else {
                    // Muestra un mensaje de advertencia sobre un nuevo producto
                    labelMensaje.setForeground(new java.awt.Color(255, 90, 0));
                    labelMensaje.setText("El producto ingresado es nuevo. Se grabar?? al registrar la venta!");
                    labelMensaje.setVisible(true);

                    con.close();
                }

            } catch (SQLException e) {
                // Si no se conecta con la DB, muestra un mensaje de error
                JOptionPane.showConfirmDialog(null, "Error de conexi??n: Base stock!", "Informaci??n", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_campoDescripcionFocusLost

    private void campoCantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoCantidadFocusGained
        fondoCantidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-cantidad2.png")));
        labelCantidad.setForeground(new java.awt.Color(255, 180, 140));
        
        if (campoCantidad.getText().equals("0")) {
            
            campoCantidad.setText("");
        }
    }//GEN-LAST:event_campoCantidadFocusGained

    private void campoCantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoCantidadFocusLost
        fondoCantidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-cantidad1.png")));
        labelCantidad.setForeground(new java.awt.Color(175, 175, 175));
        
        if (campoCantidad.getText().equals("")) {
            
            campoCantidad.setText("0");
        }
        
        // Cambio tipo de variables y caracteres para efectuar operaciones matem??ticas
        datoCantidad = campoCantidad.getText().trim();
        datoPrecioUnit = campoPrecioUnit.getText().trim();
        datoImpuestos = campoImpuestos.getText().trim();

        cantidad = Integer.parseInt(datoCantidad);
        precioUnit = Double.parseDouble(datoPrecioUnit);
        impuestos = Double.parseDouble(datoImpuestos);

        precioFinal = (cantidad * precioUnit) + impuestos;

        datoPrecioFinal = String.format("%.2f", precioFinal).replace(',', '.');

        campoPrecioFinal.setText(datoPrecioFinal);
    }//GEN-LAST:event_campoCantidadFocusLost

    private void campoPrecioUnitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoPrecioUnitFocusGained
        fondoPrecioUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-moneda2.png")));
        labelPrecioUnit.setForeground(new java.awt.Color(255, 180, 140));
        
        if (campoPrecioUnit.getText().equals("0.00")) {
            
            campoPrecioUnit.setText("");
        }
    }//GEN-LAST:event_campoPrecioUnitFocusGained

    private void campoPrecioUnitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoPrecioUnitFocusLost
        fondoPrecioUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-moneda1.png")));
        labelPrecioUnit.setForeground(new java.awt.Color(175, 175, 175));
        
        if (campoPrecioUnit.getText().equals("")) {
            
            campoPrecioUnit.setText("0.00");
        }
        
        // Cambio tipo de variables y caracteres para efectuar operaciones matem??ticas
        datoCantidad = campoCantidad.getText().trim();
        datoPrecioUnit = campoPrecioUnit.getText().trim();
        datoImpuestos = campoImpuestos.getText().trim();

        cantidad = Integer.parseInt(datoCantidad);
        precioUnit = Double.parseDouble(datoPrecioUnit);
        impuestos = Double.parseDouble(datoImpuestos);

        precioFinal = (cantidad * precioUnit) + impuestos;

        datoPrecioFinal = String.format("%.2f", precioFinal).replace(',', '.');

        campoPrecioFinal.setText(datoPrecioFinal);
    }//GEN-LAST:event_campoPrecioUnitFocusLost

    private void campoPrecioFinalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoPrecioFinalFocusGained
        fondoPrecioFinal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-impuestos2.png")));
        labelPrecioFinal.setForeground(new java.awt.Color(255, 180, 140));

        // Cambio tipo de variables y caracteres para efectuar operaciones matem??ticas
        datoCantidad = campoCantidad.getText().trim();
        datoPrecioUnit = campoPrecioUnit.getText().trim();
        datoImpuestos = campoImpuestos.getText().trim();

        cantidad = Integer.parseInt(datoCantidad);
        precioUnit = Double.parseDouble(datoPrecioUnit);
        impuestos = Double.parseDouble(datoImpuestos);

        precioFinal = (cantidad * precioUnit) + impuestos;

        datoPrecioFinal = String.format("%.2f", precioFinal).replace(',', '.');

        campoPrecioFinal.setText(datoPrecioFinal);
    }//GEN-LAST:event_campoPrecioFinalFocusGained

    private void campoPrecioFinalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoPrecioFinalFocusLost
        fondoPrecioFinal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-impuestos1.png")));
        labelPrecioFinal.setForeground(new java.awt.Color(175, 175, 175));
        
        if (campoPrecioFinal.getText().equals("")) {
            
            campoPrecioFinal.setText("0.00");
        }
    }//GEN-LAST:event_campoPrecioFinalFocusLost

    private void campoImpuestosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoImpuestosFocusGained
        fondoImpuestos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-preciofinal2.png")));
        labelImpuestos.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_campoImpuestosFocusGained

    private void campoImpuestosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoImpuestosFocusLost
        fondoImpuestos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-preciofinal1.png")));
        labelImpuestos.setForeground(new java.awt.Color(175, 175, 175));
        
        if (campoImpuestos.getText().equals("")) {
            
            campoImpuestos.setText("0.00");
        }
        
        // Cambio tipo de variables y caracteres para efectuar operaciones matem??ticas
        datoCantidad = campoCantidad.getText().trim();
        datoPrecioUnit = campoPrecioUnit.getText().trim();
        datoImpuestos = campoImpuestos.getText().trim();

        cantidad = Integer.parseInt(datoCantidad);
        precioUnit = Double.parseDouble(datoPrecioUnit);
        impuestos = Double.parseDouble(datoImpuestos);

        precioFinal = (cantidad * precioUnit) + impuestos;

        datoPrecioFinal = String.format("%.2f", precioFinal).replace(',', '.');

        campoPrecioFinal.setText(datoPrecioFinal);
    }//GEN-LAST:event_campoImpuestosFocusLost

    private void campoPuntoVentaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoPuntoVentaFocusGained
        fondoNroFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-nrofactura2.png")));
        labelNroFactura.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_campoPuntoVentaFocusGained

    private void campoPuntoVentaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoPuntoVentaFocusLost
        fondoNroFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-nrofactura1.png")));
        labelNroFactura.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_campoPuntoVentaFocusLost

    private void campoNroComprobFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoNroComprobFocusGained
        fondoNroFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-nrofactura2.png")));
        labelNroFactura.setForeground(new java.awt.Color(255, 180, 140));
    }//GEN-LAST:event_campoNroComprobFocusGained

    private void campoNroComprobFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoNroComprobFocusLost
        fondoNroFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/campos/campo-nrofactura1.png")));
        labelNroFactura.setForeground(new java.awt.Color(175, 175, 175));
    }//GEN-LAST:event_campoNroComprobFocusLost

    private void campoFechaVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoFechaVentaKeyTyped
        // Restricci??n y limitaci??n de caracteres
        char c = evt.getKeyChar();

        if ((c < '0' || c > '9') && !(c == 47)) {

            evt.consume();
        }

        if (campoFechaVenta.getText().length() >= 8) {

            evt.consume();
        }
    }//GEN-LAST:event_campoFechaVentaKeyTyped

    private void campoLetraComprobKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoLetraComprobKeyTyped
        // Restricci??n y limitaci??n de caracteres
        char c = evt.getKeyChar();

        if (Character.isLowerCase(c)) {

            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }

        if (c != 'a' && c != 'b' && c != 'c' && c != 'e' && c != 'm' && c != 'r' && c != 'x' && c != 'z'
                && c != 'A' && c != 'B' && c != 'C' && c != 'E' && c != 'M' && c != 'R' && c != 'X' && c != 'Z') {

            evt.consume();
        }

        if (campoLetraComprob.getText().length() >= 1) {

            evt.consume();
        }
    }//GEN-LAST:event_campoLetraComprobKeyTyped

    private void campoPuntoVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoPuntoVentaKeyTyped
        // Restricci??n y limitaci??n de caracteres
        char c = evt.getKeyChar();

        if (c < '0' || c > '9') {

            evt.consume();
        }

        if (campoPuntoVenta.getText().length() >= 4) {

            evt.consume();
        }
    }//GEN-LAST:event_campoPuntoVentaKeyTyped

    private void campoNroComprobKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNroComprobKeyTyped
        // Restricci??n y limitaci??n de caracteres
        char c = evt.getKeyChar();

        if (c < '0' || c > '9') {

            evt.consume();
        }

        if (campoNroComprob.getText().length() >= 8) {

            evt.consume();
        }
    }//GEN-LAST:event_campoNroComprobKeyTyped

    private void campoCodigoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCodigoClienteKeyTyped
        // Restricci??n y limitaci??n de caracteres
        char c = evt.getKeyChar();

        if (Character.isLowerCase(c)) {

            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }

        if ((c < '0' || c > '9') && (c < 'a' || c > 'z') && (c < 'A' || c > 'Z')) {

            evt.consume();
        }

        if (campoCodigoCliente.getText().length() >= 12) {

            evt.consume();
        }
    }//GEN-LAST:event_campoCodigoClienteKeyTyped

    private void campoNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNombreClienteKeyTyped
        // Restricci??n y limitaci??n de caracteres
        char c = evt.getKeyChar();

        if (Character.isLowerCase(c)) {

            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }

        if (campoNombreCliente.getText().length() >= 50) {

            evt.consume();
        }
    }//GEN-LAST:event_campoNombreClienteKeyTyped

    private void campoCondicionClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCondicionClienteKeyTyped
        // Restricci??n y limitaci??n de caracteres
        char c = evt.getKeyChar();

        if (Character.isLowerCase(c)) {

            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }

        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')) {

            evt.consume();
        }

        if (campoCondicionCliente.getText().length() >= 2) {

            evt.consume();
        }
    }//GEN-LAST:event_campoCondicionClienteKeyTyped

    private void campoCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCodigoKeyTyped
        // Restricci??n y limitaci??n de caracteres
        char c = evt.getKeyChar();

        if (Character.isLowerCase(c)) {

            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }

        if ((c < '0' || c > '9') && (c < 'a' || c > 'z') && (c < 'A' || c > 'Z')) {

            evt.consume();
        }

        if (campoCodigo.getText().length() >= 12) {

            evt.consume();
        }
    }//GEN-LAST:event_campoCodigoKeyTyped

    private void campoDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDescripcionKeyTyped
        // Restricci??n y limitaci??n de caracteres
        char c = evt.getKeyChar();

        if (Character.isLowerCase(c)) {

            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }

        if (campoDescripcion.getText().length() >= 50) {

            evt.consume();
        }
    }//GEN-LAST:event_campoDescripcionKeyTyped

    private void campoCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCantidadKeyTyped
        // Restricci??n y limitaci??n de caracteres
        char c = evt.getKeyChar();

        if ((c < '0' || c > '9') && !(c == 45)) {

            evt.consume();
        }

        if (campoCantidad.getText().length() >= 7) {

            evt.consume();
        }
    }//GEN-LAST:event_campoCantidadKeyTyped

    private void campoPrecioUnitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoPrecioUnitKeyTyped
        // Restricci??n y limitaci??n de caracteres
        char c = evt.getKeyChar();

        if ((c < '0' || c > '9') && !(c == 45) && !(c == 46)) {

            evt.consume();
        }

        if (campoPrecioUnit.getText().length() >= 10) {

            evt.consume();
        }
    }//GEN-LAST:event_campoPrecioUnitKeyTyped

    private void campoImpuestosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoImpuestosKeyTyped
        // Restricci??n y limitaci??n de caracteres
        char c = evt.getKeyChar();

        if ((c < '0' || c > '9') && !(c == 45) && !(c == 46)) {

            evt.consume();
        }

        if (campoImpuestos.getText().length() >= 10) {

            evt.consume();
        }
    }//GEN-LAST:event_campoImpuestosKeyTyped

    private void campoPrecioFinalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoPrecioFinalKeyTyped
        // Restricci??n y limitaci??n de caracteres
        char c = evt.getKeyChar();

        if ((c < '0' || c > '9') && !(c == 45) && !(c == 46)) {

            evt.consume();
        }

        if (campoPrecioFinal.getText().length() >= 10) {

            evt.consume();
        }
    }//GEN-LAST:event_campoPrecioFinalKeyTyped

    private void labelFechaVentaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFechaVentaMouseEntered
        panelFechaVentaInfo.setVisible(true);
    }//GEN-LAST:event_labelFechaVentaMouseEntered

    private void labelFechaVentaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFechaVentaMouseExited
        panelFechaVentaInfo.setVisible(false);
    }//GEN-LAST:event_labelFechaVentaMouseExited

    private void labelNroFacturaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNroFacturaMouseEntered
        panelNroFacturaInfo.setVisible(true);
    }//GEN-LAST:event_labelNroFacturaMouseEntered

    private void labelNroFacturaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNroFacturaMouseExited
        panelNroFacturaInfo.setVisible(false);
    }//GEN-LAST:event_labelNroFacturaMouseExited

    private void labelCodigoClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCodigoClienteMouseEntered
        panelCodigoClienteInfo.setVisible(true);
    }//GEN-LAST:event_labelCodigoClienteMouseEntered

    private void labelCodigoClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCodigoClienteMouseExited
        panelCodigoClienteInfo.setVisible(false);
    }//GEN-LAST:event_labelCodigoClienteMouseExited

    private void labelNombreClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNombreClienteMouseEntered
        panelNombreClienteInfo.setVisible(true);
    }//GEN-LAST:event_labelNombreClienteMouseEntered

    private void labelNombreClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNombreClienteMouseExited
        panelNombreClienteInfo.setVisible(false);
    }//GEN-LAST:event_labelNombreClienteMouseExited

    private void labelCondicionClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCondicionClienteMouseEntered
        panelCondicionClienteInfo.setVisible(true);
    }//GEN-LAST:event_labelCondicionClienteMouseEntered

    private void labelCondicionClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCondicionClienteMouseExited
        panelCondicionClienteInfo.setVisible(false);
    }//GEN-LAST:event_labelCondicionClienteMouseExited

    private void labelCodigoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCodigoMouseEntered
        panelCodigoInfo.setVisible(true);
    }//GEN-LAST:event_labelCodigoMouseEntered

    private void labelCodigoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCodigoMouseExited
        panelCodigoInfo.setVisible(false);
    }//GEN-LAST:event_labelCodigoMouseExited

    private void labelDescripcionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDescripcionMouseEntered
        panelDescripcionInfo.setVisible(true);
    }//GEN-LAST:event_labelDescripcionMouseEntered

    private void labelDescripcionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDescripcionMouseExited
        panelDescripcionInfo.setVisible(false);
    }//GEN-LAST:event_labelDescripcionMouseExited

    private void labelCantidadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCantidadMouseEntered
        panelCantidadInfo.setVisible(true);
    }//GEN-LAST:event_labelCantidadMouseEntered

    private void labelCantidadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCantidadMouseExited
        panelCantidadInfo.setVisible(false);
    }//GEN-LAST:event_labelCantidadMouseExited

    private void labelPrecioUnitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPrecioUnitMouseEntered
        panelPrecioUnitInfo.setVisible(true);
    }//GEN-LAST:event_labelPrecioUnitMouseEntered

    private void labelPrecioUnitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPrecioUnitMouseExited
        panelPrecioUnitInfo.setVisible(false);
    }//GEN-LAST:event_labelPrecioUnitMouseExited

    private void labelImpuestosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelImpuestosMouseEntered
        panelImpuestosInfo.setVisible(true);
    }//GEN-LAST:event_labelImpuestosMouseEntered

    private void labelImpuestosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelImpuestosMouseExited
        panelImpuestosInfo.setVisible(false);
    }//GEN-LAST:event_labelImpuestosMouseExited

    private void labelPrecioFinalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPrecioFinalMouseEntered
        panelPrecioFinalInfo.setVisible(true);
    }//GEN-LAST:event_labelPrecioFinalMouseEntered

    private void labelPrecioFinalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPrecioFinalMouseExited
        panelPrecioFinalInfo.setVisible(false);
    }//GEN-LAST:event_labelPrecioFinalMouseExited

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
            java.util.logging.Logger.getLogger(IngresarVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IngresarVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IngresarVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IngresarVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IngresarVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonLimpiar;
    private javax.swing.JButton botonVolver;
    private javax.swing.JTextField campoCantidad;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoCodigoCliente;
    private javax.swing.JTextField campoCondicionCliente;
    private javax.swing.JTextField campoDescripcion;
    private javax.swing.JTextField campoFechaOculta;
    private javax.swing.JTextField campoFechaVenta;
    private javax.swing.JTextField campoImpuestos;
    private javax.swing.JTextField campoLetraComprob;
    private javax.swing.JTextField campoNombreCliente;
    private javax.swing.JTextField campoNroComprob;
    private javax.swing.JTextField campoPrecioFinal;
    private javax.swing.JTextField campoPrecioUnit;
    private javax.swing.JTextField campoPuntoVenta;
    private javax.swing.JLabel fondoCantidad;
    private javax.swing.JLabel fondoCodigo;
    private javax.swing.JLabel fondoCodigoCliente;
    private javax.swing.JLabel fondoCondicionCliente;
    private javax.swing.JLabel fondoDescripcion;
    private javax.swing.JLabel fondoFechaVenta;
    private javax.swing.JLabel fondoImpuestos;
    private javax.swing.JLabel fondoIngresarVenta;
    private javax.swing.JLabel fondoMenu;
    private javax.swing.JLabel fondoNombreCliente;
    private javax.swing.JLabel fondoNroFactura;
    private javax.swing.JLabel fondoPanelIngresar1;
    private javax.swing.JLabel fondoPanelIngresar2;
    private javax.swing.JLabel fondoPrecioFinal;
    private javax.swing.JLabel fondoPrecioUnit;
    private javax.swing.JLabel labelAyuda;
    private javax.swing.JLabel labelCantidad;
    private javax.swing.JLabel labelCantidadInfo;
    private javax.swing.JLabel labelCerrar;
    private javax.swing.JLabel labelClientes;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelCodigoCliente;
    private javax.swing.JLabel labelCodigoClienteInfo;
    private javax.swing.JLabel labelCodigoInfo;
    private javax.swing.JLabel labelCompras;
    private javax.swing.JLabel labelCondicionCliente;
    private javax.swing.JLabel labelCondicionClienteInfo1;
    private javax.swing.JLabel labelCondicionClienteInfo2;
    private javax.swing.JLabel labelCondicionClienteInfo3;
    private javax.swing.JLabel labelConsultarClie;
    private javax.swing.JLabel labelConsultarComp;
    private javax.swing.JLabel labelConsultarGast;
    private javax.swing.JLabel labelConsultarProd;
    private javax.swing.JLabel labelConsultarProv;
    private javax.swing.JLabel labelConsultarVent;
    private javax.swing.JLabel labelDatosCliente;
    private javax.swing.JLabel labelDatosProducto;
    private javax.swing.JLabel labelDescripcion;
    private javax.swing.JLabel labelDescripcionInfo;
    private javax.swing.JLabel labelEliminarClie;
    private javax.swing.JLabel labelEliminarComp;
    private javax.swing.JLabel labelEliminarGast;
    private javax.swing.JLabel labelEliminarProd;
    private javax.swing.JLabel labelEliminarProv;
    private javax.swing.JLabel labelEliminarVent;
    private javax.swing.JLabel labelFechaVenta;
    private javax.swing.JLabel labelFechaVentaInfo;
    private javax.swing.JLabel labelGastos;
    private javax.swing.JLabel labelGuion1;
    private javax.swing.JLabel labelGuion2;
    private javax.swing.JLabel labelImpuestos;
    private javax.swing.JLabel labelImpuestosInfo;
    private javax.swing.JLabel labelIngresar;
    private javax.swing.JLabel labelIngresarClie;
    private javax.swing.JLabel labelIngresarComp;
    private javax.swing.JLabel labelIngresarGast;
    private javax.swing.JLabel labelIngresarProd;
    private javax.swing.JLabel labelIngresarProv;
    private javax.swing.JLabel labelIngresarVent;
    private javax.swing.JLabel labelMensaje;
    private javax.swing.JLabel labelMinimizar;
    private javax.swing.JLabel labelModificarClie;
    private javax.swing.JLabel labelModificarComp;
    private javax.swing.JLabel labelModificarGast;
    private javax.swing.JLabel labelModificarProd;
    private javax.swing.JLabel labelModificarProv;
    private javax.swing.JLabel labelModificarVent;
    private javax.swing.JLabel labelNombreCliente;
    private javax.swing.JLabel labelNombreClienteInfo;
    private javax.swing.JLabel labelNroFactura;
    private javax.swing.JLabel labelNroFacturaInfo;
    private javax.swing.JLabel labelPrecioFinal;
    private javax.swing.JLabel labelPrecioFinalInfo;
    private javax.swing.JLabel labelPrecioUnit;
    private javax.swing.JLabel labelPrecioUnitInfo;
    private javax.swing.JLabel labelProveedores;
    private javax.swing.JLabel labelReportes;
    private javax.swing.JLabel labelStock;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JLabel labelVentas;
    private javax.swing.JPanel panelCantidadInfo;
    private javax.swing.JPanel panelClientes;
    private javax.swing.JPanel panelCodigoClienteInfo;
    private javax.swing.JPanel panelCodigoInfo;
    private javax.swing.JPanel panelCompras;
    private javax.swing.JPanel panelCondicionClienteInfo;
    private javax.swing.JPanel panelDescripcionInfo;
    private javax.swing.JPanel panelFechaVentaInfo;
    private javax.swing.JPanel panelFondoIngresar;
    private javax.swing.JPanel panelGastos;
    private javax.swing.JPanel panelImpuestosInfo;
    private javax.swing.JPanel panelIngresar;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelNombreClienteInfo;
    private javax.swing.JPanel panelNroFacturaInfo;
    private javax.swing.JPanel panelPrecioFinalInfo;
    private javax.swing.JPanel panelPrecioUnitInfo;
    private javax.swing.JPanel panelProveedores;
    private javax.swing.JPanel panelStock;
    private javax.swing.JPanel panelVentas;
    private javax.swing.JSeparator separadorCliente;
    private javax.swing.JSeparator separadorProducto;
    // End of variables declaration//GEN-END:variables
}
