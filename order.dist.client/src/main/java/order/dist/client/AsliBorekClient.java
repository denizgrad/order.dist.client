package order.dist.client;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import order.dist.client.model.AsliBorekSiparisIn;
import order.dist.client.model.AsliBorekSiparisKalemIn;
import order.dist.client.model.CreateAsliBorekSiparisIn;
import order.dist.client.model.UpdateAsliBorekSiparisIn;

public class AsliBorekClient {
	private String host;
	private String userName;
	private String password;
/**
 * @param host restService's host. /createSiparis or /updateSiparis will be added in case
 * @param userName sf username defined for authentication
 * @param password sf password defined for authentication
 * <pre>
 * 		CreateAsliBorekSiparisIn newSiparis = new CreateAsliBorekSiparisIn();
		newSiparis.setAdres("TEST_ADRES_ÖÇŞİĞÜ_ığüşiöç");
		newSiparis.setAdresAciklama("TEST_ADRES_AÇIKLAMA_ÖÇŞİĞÜ_ığüşiöç");
		newSiparis.setAraToplam(new BigDecimal(33));
		newSiparis.setGenelToplam(new BigDecimal(55));
		newSiparis.setIndirim(new BigDecimal(2));
		newSiparis.setKdv(new BigDecimal(18));
		newSiparis.setPassword("password");
		newSiparis.setUserName("admin");
		newSiparis.setSfId("12345_sfID");
		newSiparis.setSiparisAdi("TEST_SIPARIS_ADI_ÖÇŞİĞÜ_ığüşiöç");
		newSiparis.setSiparisDurum("Sipariş Oluşturuldu");
		newSiparis.setSiparisOlusmaTarihi(new Date());
		newSiparis.setSiparisTalepTeslimTarihi(new Date());
		newSiparis.setSiparisVerenFirma("TEST_SIPARISI_VEREN_FIRMA_ÖÇŞİĞÜ_ığüşiöç");
		newSiparis.setTedarikEdenFirma("TEST_TEDARIK_EDEN_FIRMA_ÖÇŞİĞÜ_ığüşiöç");
		
		AsliBorekSiparisKalemIn newSiparisKalem = new AsliBorekSiparisKalemIn();
		newSiparisKalem.setAdet(new BigDecimal(2));
		newSiparisKalem.setAraToplam(new BigDecimal(33));
		newSiparisKalem.setBirimFiyati(new BigDecimal(11));
		newSiparisKalem.setIndirim(new BigDecimal(2));
		newSiparisKalem.setKalemGenelToplam(new BigDecimal(55));
		newSiparisKalem.setUrunAdi("TEST_URUN_ADI_ÖÇŞİĞÜ_ığüşiöç");
		
		newSiparis.addSiparisKalem(newSiparisKalem);
		AsliBorekClient client = new AsliBorekClient("http:/....", "admin", "pass");
		Response res = client.createSiparis(newSiparis);
		</pre>
 */
	
	
	public AsliBorekClient(String host, String userName, String password) {
		this.host = host;
		this.userName = userName;
		this.password = password;
	}
	
	public Response createSiparis(CreateAsliBorekSiparisIn createSiparis) throws Exception{
		createSiparis.setUserName(getUserName());
		createSiparis.setPassword(getPassword());
		return sendPost(createSiparis, getCreateUrl());
	}
	
	public Response updateSiparis(UpdateAsliBorekSiparisIn updateSiparis) throws Exception{
		updateSiparis.setUserName(getUserName());
		updateSiparis.setPassword(getPassword());
		return sendPost(updateSiparis, getUpdateUrl());
	}
	
	private Response sendPost(AsliBorekSiparisIn siparis, String postUrl) throws Exception {

		Gson         gson          = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
		HttpClient   httpClient    = HttpClientBuilder.create().build();
		HttpPost     post          = new HttpPost(postUrl);
		StringEntity postingString = new StringEntity(gson.toJson(siparis), ContentType.APPLICATION_JSON);
		
		post.setEntity(postingString);
		post.setHeader("Content-type", "application/json; charset=UTF-8");
		HttpResponse  response = httpClient.execute(post);
		HttpEntity responseEntity = response.getEntity();
		String strResponse = "";
		Response retResponse = null;
		if(responseEntity!=null) {
			strResponse = EntityUtils.toString(responseEntity);
			 retResponse = gson.fromJson(strResponse, Response.class);
			 System.err.println(retResponse);
		}
		return retResponse;
	}
	public static void main(String[] args) {
		CreateAsliBorekSiparisIn newSiparis = new CreateAsliBorekSiparisIn();
		newSiparis.setAdres("TEST_ADRES_ÖÇŞİĞÜ_ığüşiöç");
		newSiparis.setAdresAciklama("TEST_ADRES_AÇIKLAMA_ÖÇŞİĞÜ_ığüşiöç");
		newSiparis.setAraToplam(new BigDecimal(33));
		newSiparis.setGenelToplam(new BigDecimal(55));
		newSiparis.setIndirim(new BigDecimal(2));
		newSiparis.setKdv(new BigDecimal(18));
		newSiparis.setPassword("password");
		newSiparis.setUserName("admin");
		newSiparis.setSfId("12345_sfID");
		newSiparis.setSiparisAdi("TEST_SIPARIS_ADI_ÖÇŞİĞÜ_ığüşiöç");
		newSiparis.setSiparisDurum("Sipariş Oluşturuldu");
		newSiparis.setSiparisOlusmaTarihi(new Date());
		newSiparis.setSiparisTalepTeslimTarihi(new Date());
		newSiparis.setSiparisVerenFirma("TEST_SIPARISI_VEREN_FIRMA_ÖÇŞİĞÜ_ığüşiöç");
		newSiparis.setTedarikEdenFirma("TEST_TEDARIK_EDEN_FIRMA_ÖÇŞİĞÜ_ığüşiöç");
		
		AsliBorekSiparisKalemIn newSiparisKalem = new AsliBorekSiparisKalemIn();
		newSiparisKalem.setSiparisKalemAdi("TEST_SIPARIS_KALEM_ADI_ÖÇŞİĞÜ_ığüşiöç");
		newSiparisKalem.setAdet(new BigDecimal(2));
		newSiparisKalem.setAraToplam(new BigDecimal(33));
		newSiparisKalem.setBirimFiyati(new BigDecimal(11));
		newSiparisKalem.setIndirim(new BigDecimal(2));
		newSiparisKalem.setKalemGenelToplam(new BigDecimal(55));
		newSiparisKalem.setUrunAdi("TEST_URUN_ADI_ÖÇŞİĞÜ_ığüşiöç");
		
		newSiparis.addSiparisKalem(newSiparisKalem);
		try {
			
		
//		String postUrl 			= "http://localhost:8080" + "/order.dist/v1/siparis/islem/createSiparis";
		String postUrl 			= "http://localhost:8080" + "/order.dist/v1/siparis/islem/updateSiparis";
		Gson         gson          = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
		HttpClient   httpClient    = HttpClientBuilder.create().build();
		HttpPost     post          = new HttpPost(postUrl);
		StringEntity postingString = new StringEntity(gson.toJson(newSiparis), ContentType.APPLICATION_JSON);
		
		post.setEntity(postingString);
		post.setHeader("Content-type", "application/json; charset=UTF-8");
		
		HttpResponse  response = httpClient.execute(post);
		System.out.println(response);
		HttpEntity responseEntity = response.getEntity();
		String strResponse = "";
		Response retResponse = null;
		if(responseEntity!=null) {
			strResponse = EntityUtils.toString(responseEntity);
			 retResponse = gson.fromJson(strResponse, Response.class);
			 System.err.println(retResponse);
		}
		
		} catch (Exception e) {
			System.out.println("ERROR:" + e.getMessage());
		}
		
		System.out.println("BİTTİ");
	}
	private String getCreateUrl(){
		return this.getHost()+"/order.dist/v1/siparis/islem/createSiparis";
	}
	private String getUpdateUrl(){
		return this.getHost()+"/order.dist/v1/siparis/islem/updateSiparis";
	}
	
	//getters setters
	public String getUserName() {
		return userName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
