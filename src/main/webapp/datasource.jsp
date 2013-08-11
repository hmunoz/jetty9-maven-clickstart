<%@ page import="javax.naming.Context" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="java.io.StringWriter" %>
<%@ page import="java.io.PrintWriter" %>
<html>
<body>
<h1>DataSource demo</h1>


<h2>Sample of code</h2>
<table border="1">
    <tr>
        <td>
<pre>
Context ctx = new InitialContext();
DataSource ds = (DataSource) ctx.lookup("jdbc/mydb");
Connection conn = ds.getConnection();
ResultSet rst = stmt.executeQuery("select 1");
while (rst.next()) {
    out.print("resultset result: " + rst.getString(1));
}
rst.close();
stmt.close();
conn.close();
<code>
</code>
</pre>
        </td>
    </tr>
</table>
<p>
    <%
        Connection conn = null;
        try {
            String jndiName = request.getParameter("jndiName");
            if (jndiName == null || jndiName.isEmpty()) {
                jndiName = "jdbc/mydb";
            }
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(jndiName);
            conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rst = stmt.executeQuery("select 1");
            while (rst.next()) {
                out.print("resultset result: " + rst.getString(1));
            }
            rst.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            out.print("<code><pre>");
            e.printStackTrace(new PrintWriter(out));
            out.print("</pre></code>");
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    %>
</p>
<strong>Success! Your DataSource works!</strong>
</body>
</html>
