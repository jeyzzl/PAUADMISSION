<%@page import="java.util.List"%>
<%@page import="adm.alumno.spring.AdmEstudio"%>
<%@page import="adm.alumno.spring.AdmAcademico"%>
<%@page import="adm.alumno.spring.AdmCartaSubir"%>
<%@page import="adm.alumno.spring.AdmSolicitud"%>
<%@page import="adm.alumno.spring.AdmAcomodo"%>
<%@page import="adm.parametros.spring.AdmParametros"%>

<%@ include file= "../head.jsp"%>
<%@ include file= "../menu.jsp"%>

<head> 
    <link rel="STYLESHEET" href="<%=request.getContextPath()%>/admision.css" type="text/css">
    <style>        
        body{
            background-image: url("<%=request.getContextPath()%>/imagenes/Biblioteca.png");
            background-position: center center;
            background-attachment: fixed;
            background-repeat: no-repeat;
            background-size: cover;    
        } 
        a {text-decoration: none;} 
        a:hover {text-decoration: underline;} 
        .tabla td{padding:5px;}
        
        .tinyTip {
            width: 325px;
            padding: 17px 0px 0px 0px;
            display: block;
            background: url(js/Globo/tinyTip-top.png) 0px 0px no-repeat;
        }
        .tinyTip .content {
            padding: 0px 15px 0px 15px;
            font-size: 14px;
            font-family: "Lucida Sans Unicode";
            color: #010101;
            background: url(js/Globo/tinyTip-content.png) 0px 0px repeat-y;
        }
        .tinyTip .bottom {
            height: 47px;
            background: url(js/Globo/tinyTip-bottom.png) 0px 0px no-repeat;
            font: 0px/0px sans-serif;
        }
        .fa{
            color: black;
        }
        a> .fa{
            color: #337ab7;
        }
        
        @media (min-width: 587px) {
            .col-md-3{
                float: left;
                margin-left: 85px;
            }
        }
        @media (min-width: 764px) {
            .col-md-3{
                float: left;
                margin-left: 0;
            }        
        }
        @media (min-width: 480px) {
            .col-md-5{
                float: left;
            }
        }    
    </style>    
</head>
<%    
    String folio            = (String)session.getAttribute("Folio")==null?"0":(String)session.getAttribute("Folio");
    List<String> opciones    = (List<String>)session.getAttribute("Opciones");
    boolean existeAcomodo    = (boolean)request.getAttribute("existeAcomodo");
    String colorGrabarAcomodo    = existeAcomodo?"style='color:green'":"style='color:orange'";
    String mensaje                 = request.getParameter("Mensaje")==null?"":request.getParameter("Mensaje");
    String tipoAcomodo             = (String)request.getAttribute("tipoAcomodo");
    String genero                 = (String)request.getAttribute("genero");

    AdmSolicitud admSolicitud         = (AdmSolicitud)request.getAttribute("admSolicitud");
    AdmParametros admParametros     = (AdmParametros)request.getAttribute("admParametros");

    String institucion = admParametros.getInstitucion();
    // System.out.println(institucion);
    
    List<AdmAcomodo> lisAcomodos     = (List<AdmAcomodo>)request.getAttribute("lisAcomodos");
%>
<script type="text/javascript">
    // Define all possible options with their corresponding IDs
    const tipoAcomodoOptions = {
        // For Single Nacional students (N)
        "N_S_M_01": [ {text: "DSC - Day Student living on campus.", value: "6"}, {text: "DST - Day Student with transport provided.", value: "7"}, {text: "DSTN - Day Student without transport.", value: "8"} ],
        "N_S_M_03": [ {text: "Men's Dorm (MD)", value: "5"} ],
        "N_S_F_01": [ {text: "DSC - Day Student living on campus.", value: "6"}, {text: "DST - Day Student with transport provided.", value: "7"}, {text: "DSTN - Day Student without transport.", value: "8"} ],
        "N_S_F_02": [ {text: "Ladies Dorm (LD)", value: "4"} ],
        
        // For Married Nacional students (N)
        "N_C_M_04": [ {text: "DST - Day Student with transport provided.", value: "7"}, {text: "DSTN - Day Student without transport.", value: "8"}],
        "N_C_M_05": [ {text: "MSV1- 1 Bedroom", value: "1"}, {text: "MSV2- 2 Bedrooms", value: "2"}, {text: "MSV3- 3 Bedrooms", value: "3"} ],
        "N_C_F_04": [ {text: "DST - Day Student with transport provided.", value: "7"}, {text: "DSTN - Day Student without transport.", value: "8"}],
        "N_C_F_05": [ {text: "MSV1- 1 Bedroom", value: "1"}, {text: "MSV2- 2 Bedrooms", value: "2"}, {text: "MSV3- 3 Bedrooms", value: "3"} ],

        // For Divorced Nacional students (N)
        "N_D_M_06": [ {text: "DSC - Day Student living on campus.", value: "6"}, {text: "DST - Day Student with transport provided.", value: "7"}, {text: "DSTN - Day Student without transport.", value: "8"} ],
        "N_D_F_06": [ {text: "DSC - Day Student living on campus.", value: "6"}, {text: "DST - Day Student with transport provided.", value: "7"}, {text: "DSTN - Day Student without transport.", value: "8"} ],

        // For Widowed Nacional students (N)
        "N_V_M_07": [ {text: "DSC - Day Student living on campus.", value: "6"}, {text: "DST - Day Student with transport provided.", value: "7"}, {text: "DSTN - Day Student without transport.", value: "8"}],
        "N_V_M_08": [ {text: "MSV1- 1 Bedroom", value: "1"}, {text: "MSV2- 2 Bedrooms", value: "2"}, {text: "MSV3- 3 Bedrooms", value: "3"} ],
        "N_V_F_07": [ {text: "DSC - Day Student living on campus.", value: "6"}, {text: "DST - Day Student with transport provided.", value: "7"}, {text: "DSTN - Day Student without transport.", value: "8"}],
        "N_V_F_08": [ {text: "MSV1- 1 Bedroom", value: "1"}, {text: "MSV2- 2 Bedrooms", value: "2"}, {text: "MSV3- 3 Bedrooms", value: "3"} ],
        
        // For Internacional students (I)
        "I_S_M_01": [ {text: "N/A", value: "9"} ],
        "I_S_M_03": [ {text: "Men's Dorm (MD)", value: "5"} ],
        "I_S_F_01": [ {text: "N/A", value: "9"} ],
        "I_S_F_02": [ {text: "Ladies Dorm (LD)", value: "4"} ],
        
        // For Married Internacional students (I)
        "I_C_M_04": [ {text: "N/A", value: "9"}],
        "I_C_F_04": [ {text: "N/A", value: "9"}],
        "I_C_M_05": [ {text: "MSV1- 1 Bedroom", value: "1"}, {text: "MSV2- 2 Bedrooms", value: "2"}, {text: "MSV3- 3 Bedrooms", value: "3"} ],
        "I_C_F_05": [ {text: "MSV1- 1 Bedroom", value: "1"}, {text: "MSV2- 2 Bedrooms", value: "2"}, {text: "MSV3- 3 Bedrooms", value: "3"} ],

        // For Divorced Internacional students (I)
        "I_D_M_06": [ {text: "DSC - Day Student living on campus.", value: "6"}, {text: "DST - Day Student with transport provided.", value: "7"}, {text: "DSTN - Day Student without transport.", value: "8"} ],
        "I_D_F_06": [ {text: "DSC - Day Student living on campus.", value: "6"}, {text: "DST - Day Student with transport provided.", value: "7"}, {text: "DSTN - Day Student without transport.", value: "8"} ],

        // For Widowed Internacional students (N)
        "I_V_M_07": [ {text: "DSC - Day Student living on campus.", value: "6"}, {text: "DST - Day Student with transport provided.", value: "7"}, {text: "DSTN - Day Student without transport.", value: "8"}],
        "I_V_M_08": [ {text: "MSV1- 1 Bedroom", value: "1"}, {text: "MSV2- 2 Bedrooms", value: "2"}, {text: "MSV3- 3 Bedrooms", value: "3"} ],
        "I_V_F_07": [ {text: "DSC - Day Student living on campus.", value: "6"}, {text: "DST - Day Student with transport provided.", value: "7"}, {text: "DSTN - Day Student without transport.", value: "8"}],
        "I_V_F_08": [ {text: "MSV1- 1 Bedroom", value: "1"}, {text: "MSV2- 2 Bedrooms", value: "2"}, {text: "MSV3- 3 Bedrooms", value: "3"} ]
    };

    const tipoAcomodoOptionsSonoma = {
        // For Single Nacional students (N)
        "N_S_M_01": [ {text: "DSTN - Day Student without transport.", value: "8"} ],
        "N_S_M_03": [ {text: "Men's Dorm (MD)", value: "5"} ],
        "N_S_F_01": [ {text: "DSTN - Day Student without transport.", value: "8"} ],
        "N_S_F_02": [ {text: "Ladies Dorm (LD)", value: "4"} ],
        
        // For Married Nacional students (N)
        "N_C_M_04": [ {text: "DSTN - Day Student without transport.", value: "8"}],
        "N_C_M_05": [ {text: "MSV- 1 Bedroom", value: "1"}, {text: "Men's Dorm (MD)", value: "5"}],
        "N_C_F_04": [ {text: "DSTN - Day Student without transport.", value: "8"}],
        "N_C_F_05": [ {text: "MSV- 1 Bedroom", value: "1"}, {text: "Ladies Dorm (LD)", value: "4"}],

        // For Divorced Nacional students (N)
        "N_D_M_06": [ {text: "DSTN - Day Student without transport.", value: "8"} ],
        "N_D_F_06": [ {text: "DSTN - Day Student without transport.", value: "8"} ],

        // For Widowed Nacional students (N)
        "N_V_M_07": [ {text: "DSTN - Day Student without transport.", value: "8"}],
        "N_V_M_08": [ {text: "MSV- 1 Bedroom", value: "1"}],
        "N_V_F_07": [ {text: "DSTN - Day Student without transport.", value: "8"}],
        "N_V_F_08": [ {text: "MSV- 1 Bedroom", value: "1"}],
        
        // For Internacional students (I)
        "I_S_M_01": [ {text: "N/A", value: "9"} ],
        "I_S_M_03": [ {text: "Men's Dorm (MD)", value: "5"} ],
        "I_S_F_01": [ {text: "N/A", value: "9"} ],
        "I_S_F_02": [ {text: "Ladies Dorm (LD)", value: "4"} ],
        
        // For Married Internacional students (I)
        "I_C_M_04": [ {text: "N/A", value: "9"}],
        "I_C_F_04": [ {text: "N/A", value: "9"}],
        "I_C_M_05": [ {text: "MSV- 1 Bedroom", value: "1"}, {text: "Men's Dorm (MD)", value: "5"}],
        "I_C_F_05": [ {text: "MSV- 1 Bedroom", value: "1"}, {text: "Ladies Dorm (LD)", value: "4"}],

        // For Divorced Internacional students (I)
        "I_D_M_06": [ {text: "DSTN - Day Student without transport.", value: "8"} ],
        "I_D_F_06": [ {text: "DSTN - Day Student without transport.", value: "8"} ],

        // For Widowed Internacional students (N)
        "I_V_M_07": [ {text: "DSTN - Day Student without transport.", value: "8"}],
        "I_V_M_08": [ {text: "MSV- 1 Bedroom", value: "1"}],
        "I_V_F_07": [ {text: "DSTN - Day Student without transport.", value: "8"}],
        "I_V_F_08": [ {text: "MSV- 1 Bedroom", value: "1"}]
    };

    function updateTipoAcomodoOptions() {
        const acomodoId = document.getElementById("acomodoId").value;
        const tipoAplicante = "<%=admSolicitud.getTipoAplicante()%>";
        const tipoAcomodoParam = "<%=tipoAcomodo%>"; // "S" or "C"
        const genero = "<%=genero%>";
        const inst = document.getElementById("Institucion").value;

        // Debug: Print all values
        // console.log("Debug Values ----");
        // console.log("acomodoId:", acomodoId);
        // console.log("tipoAplicante:", tipoAplicante);
        // console.log("tipoAcomodoParam:", tipoAcomodoParam);
        // console.log("genero:", genero);
        // console.log("inst:", inst);
        
        // Create the key to look up the options
        const key = tipoAplicante + "_" + tipoAcomodoParam + "_" + genero + "_" + acomodoId;

        //const options = tipoAcomodoOptions[key] || [];

        // Get the appropriate options (empty array if no match)
        //if(inst === 'Sonoma') {
          // options = tipoAcomodoOptionsSonoma[key] || [];
        // }
        let options = [];
        if(inst === 'Sonoma') {
            options = tipoAcomodoOptionsSonoma[key] || [];
        } else {
            options = tipoAcomodoOptions[key] || [];
        }

        // console.log("size:", options.length);
        
        // Update the tipoAcomodo select
        const tipoAcomodoSelect = document.getElementById("tipoAcomodo");
        tipoAcomodoSelect.innerHTML = '';
        
        // Add new options
        options.forEach(option => {
            const optElement = document.createElement("option");
            optElement.value = option.value;
            optElement.textContent = option.text;
            if (option.value === "<%=admSolicitud.getTipoAcomodo()%>") {
                optElement.selected = true;
            }
            tipoAcomodoSelect.appendChild(optElement);
        });
    }

    // Initialize on page load
    document.addEventListener("DOMContentLoaded", function() {
        document.getElementById("acomodoId").addEventListener("change", updateTipoAcomodoOptions);
        updateTipoAcomodoOptions();
    });

    function grabar() {
        if (revisa()) {
            document.frmAcomodo.submit();
        }
    }

    function revisa() {
        if (document.getElementById("acomodoId").value === "0") {
            alert("Please select your preferred accommodation");
            document.getElementById("acomodoId").focus();
            return false;
        }
        
        const tipoAcomodoSelect = document.getElementById("tipoAcomodo");
        if (tipoAcomodoSelect.options.length === 0) {
            alert("No valid options available for your selection");
            return false;
        }
        
        return true;
    }
</script>
<body>
    <div class="container-fluid">
        <div class="row">
            <%-- <%@ include file= "../opciones.jsp"%> --%>
            <div class="col-lg-12" style="background-color:white; min-height:calc(100vh - 37px);">
                <div>                                                                                                          <!-- DIV DE ACOMODO PREFERIDO -->
                    <div class="d-flex bd-highlight page-header">
                        <div class="p-2 w-100 bd-highlight">
                            <h2><i class="fas fa-check-circle" aria-hidden="true" <%=colorGrabarAcomodo%>></i>&nbsp;<spring:message code="solicitud.acomodo.Acomodo"/></h2>
                        </div>
                        <div class="p-2 flex-shrink-1 bd-highlight">
                            <div class="d-flex align-items-center">
                                <a href="<%=request.getContextPath()%>/solicitud/otroEstudio"><i class="fas fa-caret-left fa-3x"></i></a>
                                &nbsp;<b class="fs-5">5/12</b>&nbsp;
    <%                    if(existeAcomodo){ %>
                                <a href="<%=request.getContextPath()%>/solicitud/salud"><i class="fas fa-caret-right fa-3x"></i></a>
    <%                    }else{
                            out.print("&nbsp;");
                        }%>             
                            </div>
                        </div>
                    </div>
                    <div class="alert alert-info">
                        <b><spring:message code="solicitud.acomodo.Instruccion"/></b>
                    </div>
                    <form id="frmAcomodo" name="frmAcomodo" action="grabarAcomodo" method="post">
                        <input type="hidden" name="Institucion" id="Institucion" value="<%=institucion%>">
                        <label class="mt-3"><b>Residence Type</b></label>
                        <select class="form-select mb-4" style="width: 35rem" id="acomodoId" name="acomodoId">                        <!-- ACOMODO ID -->
<%  for(AdmAcomodo acomodo : lisAcomodos){    %>
                            <option value="<%=acomodo.getAcomodoId()%>" <%=admSolicitud.getAcomodoId().equals(acomodo.getAcomodoId())?"selected":""%>><%=acomodo.getNombreAcomodo()%></option>
<%  }        %>
                        </select>
                        <label class="mt-3"><b><spring:message code="solicitud.acomodo.Titulo"/></b></label>
                        <select class="form-select mb-4" style="width: 35rem" id="tipoAcomodo" name="tipoAcomodo">
                            <!-- Options will be populated by JavaScript -->
                        </select>
<% if(admSolicitud.getEstado().equals("1")){%>
<%                if(existeAcomodo){%>
                        <a href="borrarAcomodo?acomodoId=<%=admSolicitud.getAcomodoId()%>" class="btn alert-warning text-decoration-none me-3">Remove Selection</a>
<%                }%>
                        <button type="button" onclick="grabar();" class="btn alert-info"><spring:message code="adm.Guardar"/></button>
<% }%>    
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
<% if (mensaje.equals("1")){ %>
    <meta http-equiv="refresh" content="1;url=salud" />
<% } %>