package filtro;

import beans.usuarioBean;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Todas as requisições dos clientes para qualquer página que esteja dentro de
 * /seguranca/, vai passar por esse filtro, que vai interceptar a requisição e
 * verificar se o usuário está logado. Se usuário não estiver logado,redireciona
 * para uma página de erro (metodo doFilter()). Obs: ver o "url-pattern" e
 * "welcome-file" do arquivo web.xml que foram ajustados.
 */
@WebFilter("/seguranca/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("==== Entrou no Filtro. Alguém está tentando acessar uma página segura..");

        usuarioBean usuarioBean = (usuarioBean) ((HttpServletRequest) request).getSession().getAttribute("usuarioBean");

        if (usuarioBean == null || !usuarioBean.isLogado()) {
            System.out.println("==== Acesso a pagina negado: usuario nao esta logado.");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/erroLogin.xhtml");
            dispatcher.forward(request, response);
        } else {
            System.out.println("==== Acesso permitido. Usuario esta logado. Login: " + usuarioBean.getCpf());
            String url = ((HttpServletRequest) request).getServletPath();

            System.out.println(usuarioBean.getVotou());

            if ("1".equals(usuarioBean.getVotou())) {
//Já votou
                if ((url.equals("/seguranca/votacaoPresidente.xhtml")
                        || url.equals("/seguranca/votacaoGovernador.xhtml")
                        || url.equals("/seguranca/votacaoPrefeito.xhtml"))) {

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/seguranca/resultados.xhtml");
                    dispatcher.forward(request, response);
                }
            } else {
//Não votou
                if (url.equals("/seguranca/resultados.xhtml")) {
                    System.out.println("==== Fim da votação ====");
                    usuarioBean.hasVoted();
                }
            }
            //Continua a execução do request
            chain.doFilter(request, response);
        }
    }
}
