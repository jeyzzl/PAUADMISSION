package adm;

import java.util.Locale;
import java.util.Properties;

import jakarta.servlet.DispatcherType;
import javax.sql.DataSource;

//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class ConConfiguration implements WebMvcConfigurer{
	
	@Bean(name = "dataSourceSalomon")	
	@Primary
	public DataSource dataSourceSalomon() {
		
		HikariDataSource hikariDataSource = new HikariDataSource();
		//  hikariDataSource.setJdbcUrl("jdbc:oracle:thin:@74.208.80.211:1521:xe");
		// hikariDataSource.setJdbcUrl("jdbc:oracle:thin:@172.16.203.160:1521:xe");
		 hikariDataSource.setJdbcUrl("jdbc:oracle:thin:@172.16.209.247:1521:xe");
		//  hikariDataSource.setJdbcUrl("jdbc:oracle:thin:@172.16.209.245:1521:xe");
		hikariDataSource.setUsername("enoc");
		hikariDataSource.setPassword("caminacondios");
		hikariDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		hikariDataSource.setMaximumPoolSize(14);
		hikariDataSource.setMinimumIdle(1);
		hikariDataSource.setIdleTimeout(49000);
		hikariDataSource.setLeakDetectionThreshold(77000);
		hikariDataSource.setMaxLifetime(1200000);
		hikariDataSource.setAutoCommit(true);	 
		
		return hikariDataSource;		
	}
	
	@Bean(name = "jdbcSalomon")
	public JdbcTemplate jdbcSalomon(){
		return new JdbcTemplate(dataSourceSalomon());
	}
	
	@Bean(name = "dataSourceAdm")	
	public DataSource dataSourceAdm() {
		
		HikariDataSource hikariDataSource = new HikariDataSource();
		// hikariDataSource.setJdbcUrl("jdbc:postgresql://127.0.0.1:5432/academico");
		// hikariDataSource.setJdbcUrl("jdbc:postgresql://172.16.203.160:5432/academico");
		hikariDataSource.setJdbcUrl("jdbc:postgresql://172.16.209.247:5432/academico");
		// hikariDataSource.setJdbcUrl("jdbc:postgresql://172.16.209.245:5432/academico");
		hikariDataSource.setUsername("postgres");
		hikariDataSource.setPassword("ptstann69");
		hikariDataSource.setDriverClassName("org.postgresql.Driver");
		hikariDataSource.setMaximumPoolSize(14);
		hikariDataSource.setMinimumIdle(1);
		hikariDataSource.setIdleTimeout(49000);
		hikariDataSource.setLeakDetectionThreshold(0);
		hikariDataSource.setMaxLifetime(1200000);
		hikariDataSource.setAutoCommit(true);	
		
		return hikariDataSource;
	}	
	 
	@Bean(name = "jdbcAdm")
	public JdbcTemplate jdbcAdm() {
		return new JdbcTemplate(dataSourceAdm());
	}
	
	@Bean(name = "dataSourceExa")	
	public DataSource dataSourceExa() {
		
		HikariDataSource hikariDataSource = new HikariDataSource();
		// hikariDataSource.setJdbcUrl("jdbc:postgresql://127.0.0.1:5432/examen_um");
		// hikariDataSource.setJdbcUrl("jdbc:postgresql://172.16.203.160:5432/examen_um");
		hikariDataSource.setJdbcUrl("jdbc:postgresql://172.16.209.247:5432/examen_um");
		// hikariDataSource.setJdbcUrl("jdbc:postgresql://172.16.209.245:5432/examen_um");
		hikariDataSource.setUsername("postgres");
		hikariDataSource.setPassword("ptstann69");
		hikariDataSource.setDriverClassName("org.postgresql.Driver");
		hikariDataSource.setMaximumPoolSize(14);
		hikariDataSource.setMinimumIdle(1);
		hikariDataSource.setIdleTimeout(49000);
		hikariDataSource.setLeakDetectionThreshold(0);
		hikariDataSource.setMaxLifetime(1200000);
		hikariDataSource.setAutoCommit(true);
		
		return hikariDataSource;
	}	
	
	@Bean(name = "jdbcExa")
	public JdbcTemplate jdbcExa() {
		return new JdbcTemplate(dataSourceExa());
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		localeInterceptor.setIgnoreInvalidLocale(true);
		localeInterceptor.setParamName("IdiomaUsuario");
		return localeInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
	}
	
	@Bean
	public FilterRegistrationBean<MyFilter> myFilterBean() {
	  final FilterRegistrationBean<MyFilter> filterRegBean = new FilterRegistrationBean<MyFilter>();
	  filterRegBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD);	  	  
	  filterRegBean.addUrlPatterns("/WEB-INF/jsp/*");
	  filterRegBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,*.pdf,/fontawesome/*");
	  //filterRegBean.setEnabled(Boolean.TRUE);
	  filterRegBean.setName("My Filtro");
	  filterRegBean.setAsyncSupported(Boolean.TRUE);
	  //filterRegBean.setOrder(1);
	  filterRegBean.setFilter(new MyFilter());
	  return filterRegBean;
	}
	
	/*
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername("my.gmail@gmail.com");
	    mailSender.setPassword("password");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
	*/
} 
