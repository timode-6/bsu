<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default2.aspx.cs" Inherits="Default2" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <asp:TextBox ID="WelcomeText" runat="server" Width="150pt" 
            Text="Добро пожаловать в ASP.NET!"></asp:TextBox>
        <br/>
        <asp:Image ID="Pic" runat="server" ImageUrl="Moon.jpg"/>
        <br/>
    </form>
</body>
</html>
