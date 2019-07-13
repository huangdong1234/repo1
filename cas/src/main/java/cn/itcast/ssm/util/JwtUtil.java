package cn.itcast.ssm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.RandomStringUtils;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Xuejun Yang
 * @version V1.0
 * @description: TODO
 * @date 2019/5/31 11:32
 */
public class JwtUtil {

    public static  final String key="79e7c69681b8270162386e6daa53d1dc";
    /**
     * 解密
     * @param jsonWebToken
     * @param base64Security
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) {

                Claims claims = Jwts.parser()
                        .setSigningKey(base64Security.getBytes())
                        .parseClaimsJws(jsonWebToken).getBody();

                return claims;
    }




    /**
     * 前三个参数为自己用户token的一些信息比如id，权限，名称等。不要将隐私信息放入（大家都可以获取到）
     * @param map
     * @param base64Security
     * @return
     */
    public static String createJWT(Map<String, Object> map, String base64Security) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        ObjectMapper objectMapper=new ObjectMapper();


        //添加构成JWT的参数
        JwtBuilder builder = null; //估计是第三段密钥
        try {
            builder = Jwts.builder().setHeaderParam("typ", "JWT")
                    .setClaims(map)
                    .signWith(signatureAlgorithm,base64Security.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //生成JWT
        return builder.compact();
    }

    public static void main(String[] args) throws InterruptedException, NoSuchAlgorithmException {

        String random = RandomStringUtils.random(10,true,true);

        String md5 = DigestUtil.getMD5("你好".getBytes());
        System.out.println(md5);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", "960302601");
        map.put("name", "yangxuejun");

        //密钥
        String keyt = "79e7c69681b8270162386e6daa53d1dc";
        String token=JwtUtil.createJWT(map, keyt);
        System.out.println("JWT加密的结果："+ token);
        System.out.println("JWT解密的结果："+ parseJWT(token, keyt));
    }

}
