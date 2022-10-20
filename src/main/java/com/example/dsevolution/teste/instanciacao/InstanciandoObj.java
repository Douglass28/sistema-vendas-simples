package com.example.dsevolution.teste.instanciacao;

import com.example.dsevolution.teste.entities.*;
import com.example.dsevolution.teste.entities.enums.StatusPagamento;
import com.example.dsevolution.teste.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

import static com.example.dsevolution.teste.entities.enums.Perfil.ROLE_ADMIN;
import static com.example.dsevolution.teste.entities.enums.Perfil.ROLE_USER;

@Configuration
public class InstanciandoObj implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public void run(String... args) throws Exception {

        Cliente cli1 = new Cliente(null, "Douglas", "douglas.26@gmail.com","48366063062");
        Cliente cli2 = new Cliente(null, "Daniel", "daniel.6@gmail.com","963852456");


        Pedido pedido1 = new Pedido(null, Instant.now(), cli1, StatusPagamento.AGUARDANDO_PAGAMENTO);
        Pedido pedido2 = new Pedido(null, Instant.now(), cli2, StatusPagamento.PAGO);

        cli1.getPedidos().add(pedido1);
        cli2.getPedidos().add(pedido2);

        clienteRepository.saveAll(Arrays.asList(cli1, cli2));
        pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));


        Produto prod1 = new Produto(null, "apple watch", 1250.0, "aplle watch branco 42mm");
        Produto prod2 = new Produto(null, "notbook i7", 3500.0, "notbook ultima geração");
        Produto prod3 = new Produto(null, "seja-foda", 125.0, "feliz, otimista, determinado, abumdante");

        produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));

        Roles role1 = new Roles(null, ROLE_USER);
        Roles role2 = new Roles(null, ROLE_ADMIN);

        UsuarioSistema usuarioSistema1 = new UsuarioSistema(null, "camila", "nova123");
        UsuarioSistema usuarioSistema2 = new UsuarioSistema(null, "carol", "nova123");
        String password1 = passwordEncoder.encode(usuarioSistema1.getPassword());
        String password2 = passwordEncoder.encode(usuarioSistema2.getPassword());
        usuarioSistema1.setPassword(password1);
        usuarioSistema2.setPassword(password2);


        usuarioSistema1.getRoles().add(role1);
        usuarioSistema2.getRoles().add(role2);
        rolesRepository.saveAll(Arrays.asList(role1, role2));
        usuarioRepository.saveAll(Arrays.asList(usuarioSistema1, usuarioSistema2));

    }
}
