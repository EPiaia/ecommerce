/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce.beans;

import com.mycompany.ecommerce.domains.Cliente;
import com.mycompany.ecommerce.domains.Usuario;
import com.mycompany.ecommerce.services.ClienteService;
import com.mycompany.ecommerce.services.UsuarioService;
import com.mycompany.ecommerce.utils.JsfUtil;
import com.mycompany.ecommerce.utils.StringUtil;
import com.mycompany.ecommerce.utils.TipoAcessoEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Eduar
 */
@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

    @EJB
    private UsuarioService usuarioService;
    @EJB
    private ClienteService clienteService;

    private Cliente cliente = new Cliente();
    private String login;
    private String senha;

    public void cadastrarCliente() {
        if (!isClienteValido()) {
            return;
        }
        Usuario usuario = new Usuario();
        usuario.setUsrLogin(login);
        usuario.setUsrSenha(StringUtil.MD5(senha));
        if (cliente.getCliNome().length() > 50) {
            usuario.setUsrDesc(cliente.getCliNome().substring(0, 49));
        } else {
            usuario.setUsrDesc(cliente.getCliNome());
        }
        usuario.setUsrEmail(cliente.getCliEmail());
        usuario.setUsrTpAcesso(TipoAcessoEnum.CLIENTE.getTipoAcessoCodigo());
        usuario = usuarioService.save(usuario);
        String cpf = this.cliente.getCliCpf();
        cpf = cpf.replace(".", "").replace("-", "");
        this.cliente.setCliCpf(cpf);
        String telefone = this.cliente.getCliTelefone();
        telefone = telefone.replace("(", "").replace(")", "").replace("-", "");
        this.cliente.setCliTelefone(telefone);
        this.cliente.setCliUsuario(usuario);
        clienteService.save(this.cliente);
        JsfUtil.redirect("/Ecommerce/login.xhtml");
    }

    public boolean isClienteValido() {
        if (isExisteUsuarioComLogin()) {
            JsfUtil.warn("Já existe um usuário com o login digitado");
            return false;
        }
        if (this.senha.length() < 6) {
            JsfUtil.warn("A senha deve possui entre 6 e 12 caracteres");
            return false;
        }
        String cpf = this.cliente.getCliCpf();
        cpf = cpf.replace(".", "").replace("-", "");
        if (!StringUtil.isCPFValido(cpf)) {
            JsfUtil.warn("O CPF informado não é válido");
            return false;
        }
        List<Cliente> clientes = new ArrayList<>();
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("cliCpf", cpf);
        clientes = clienteService.filtrar(filtros);
        if (cliente != null && !clientes.isEmpty()) {
            JsfUtil.warn("Um cliente com este CPF já está cadastrado");
            return false;
        }
        filtros.clear();
        filtros.put("cliEmail", cliente.getCliEmail());
        clientes = clienteService.filtrar(filtros);
        if (cliente != null && !clientes.isEmpty()) {
            JsfUtil.warn("Um cliente com este e-mail já está cadastrado");
            return false;
        }
        return true;
    }

    public boolean isExisteUsuarioComLogin() {
        return usuarioService.getUsuarioPorLogin(login) != null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
