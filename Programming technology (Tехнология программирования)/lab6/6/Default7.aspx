<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default7.aspx.cs" Inherits="Default7" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <asp:Label ID="lblMessage" runat="server" Text="" 
                Font-Size="15pt" ForeColor="#cc00cc"/>
            <br/>
            <asp:Label ID="HTMLHello" runat="server" Text="HTML Здравствуй, мир!" 
                Font-Size="15pt" ForeColor="#ff0000"/>
            <br/>
            <asp:Label ID="JavaScriptHello" runat="server" Text="JAVASCRIPT Здравствуй, мир!" 
                Font-Size="15pt" ForeColor="#009933"/>
            <br/>
            <asp:Label ID="VBNETHello" runat="server" Text="VB.NET Здравствуй, мир!" 
                Font-Size="15pt" ForeColor="#0000ff"/>
            <br/>
            <asp:Label ID="CSharpHello" runat="server" Text="C# Здравствуй, мир!" 
                Font-Size="15pt" ForeColor="#ffff00"/>
        </div>
    </form>
</body>
</html>
