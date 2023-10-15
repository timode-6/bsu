
Partial Class Default4
    Inherits System.Web.UI.Page

    'Multiplies numbers entered in TextBox elements and writes result to the Result Label.
    Protected Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        Result.Visible = True
        Result.Text = CInt(Number1TB.Text) * CInt(Number2TB.Text)
    End Sub
End Class
